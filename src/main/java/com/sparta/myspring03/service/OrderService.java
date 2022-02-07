package com.sparta.myspring03.service;

import com.sparta.myspring03.model.Food;
import com.sparta.myspring03.model.FoodOrder;
import com.sparta.myspring03.model.Ordering;
import com.sparta.myspring03.model.Restaurant;
import com.sparta.myspring03.repository.FoodOrderRepository;
import com.sparta.myspring03.repository.FoodRepository;
import com.sparta.myspring03.repository.OrderRepository;
import com.sparta.myspring03.repository.RestaurantRepository;
import com.sparta.myspring03.requestDto.FoodOrderRequestDto;
import com.sparta.myspring03.requestDto.OrderRequestDto;
import com.sparta.myspring03.responseDto.FoodOrderResponseDto;
import com.sparta.myspring03.responseDto.OrderResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final FoodOrderRepository foodOrderRepository;
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    public OrderService(
            OrderRepository orderRepository,
            FoodOrderRepository foodOrderRepository,
            FoodRepository foodRepository,
            RestaurantRepository restaurantRepository
    ) {
        this.orderRepository = orderRepository;
        this.foodOrderRepository = foodOrderRepository;
        this.foodRepository = foodRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public OrderResponseDto saveOrder(OrderRequestDto orderRequestDto) {

        // 입력받은 레스토랑 아이디
        Long restaurantId = orderRequestDto.getRestaurantId();

        // 입력받은 음식 주문 리스트
        List<FoodOrderRequestDto> foods = orderRequestDto.getFoods();

        // 주문번호 디비에 저장
        Ordering ordering = new Ordering(restaurantId);
        Ordering savedOrdering = orderRepository.save(ordering);

        // 한 주문에 들어온 음식 주문 리스트 디비에 저장하는 과정
        // 1. foods의 ID들을 토대로 디비에서 food 리스트 불러오기
        List<Long> foodsId = new ArrayList<>();
        for (FoodOrderRequestDto foodOrderRequestDto : foods) {
            foodsId.add(foodOrderRequestDto.getId());
        }
        List<Food> foundFood = foodRepository.findAllById(foodsId);

        // 2. foodOrderRequestDto에 food 추가, Order 추가해주면서 FoodOrder 리스트 만들기
        List<FoodOrder> foodOrders = new ArrayList<>();
        for (int i=0; i<foods.size();i++) {
            foods.get(i).setFood(foundFood.get(i));
            foods.get(i).setOrdering(ordering);
            FoodOrder foodOrder = new FoodOrder(foods.get(i));
            foodOrders.add(foodOrder);
        }
        // 3. 모든 foodOrder 저장
        List<FoodOrder> savedFoodOrders = foodOrderRepository.saveAll(foodOrders);

        // FoodOrderResponseDto 만들기
        List<FoodOrderResponseDto> foodOrderResponseDtos = new ArrayList<>();
        for (int i=0; i<savedFoodOrders.size(); i++) {
            String name = savedFoodOrders.get(i).getFood().getName();
            int quantity = savedFoodOrders.get(i).getQuantity();
            int price = savedFoodOrders.get(i).getFood().getPrice() * quantity;
            FoodOrderResponseDto responseDto = new FoodOrderResponseDto(name, quantity, price);
            foodOrderResponseDtos.add(responseDto);
        }

        // OrderResponseDto 만들기
        Restaurant restaurant = restaurantRepository.findById(savedOrdering.getRestaurantId())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 식당입니다."));
        String restaurantName = restaurant.getName();
        int deliveryFee = restaurant.getDeliveryFee();
        int totalPrice = 0;
        totalPrice += deliveryFee;
        for (FoodOrderResponseDto foodOrderResponseDto : foodOrderResponseDtos) {
            totalPrice += foodOrderResponseDto.getPrice();
        }

         OrderResponseDto orderResponseDto = new OrderResponseDto(
                restaurantName,
                foodOrderResponseDtos,
                deliveryFee,
                totalPrice);


        return orderResponseDto;
    }
}
