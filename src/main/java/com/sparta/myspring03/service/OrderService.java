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

        // 주문 생성
        Ordering ordering = new Ordering(restaurantId);


        // 음식 주문(FoodOrder) 리스트 만들기
        // 1. foods의 음식주문들의 수량체크 + 해당 음식의 아이디를 foodsId에 추가
        List<Long> foodsId = new ArrayList<>();
        for (FoodOrderRequestDto foodOrderRequestDto : foods) {
            int quantity = foodOrderRequestDto.getQuantity();
            if(quantity<1||quantity>100) {
                throw new IllegalArgumentException("음식 수량은 1~100 사이로 입력해주세요.");
            }
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


        // FoodOrderResponseDto 만들기
        List<FoodOrderResponseDto> foodOrderResponseDtos = new ArrayList<>();
        for (int i=0; i<foodOrders.size(); i++) {
            String name = foodOrders.get(i).getFood().getName();
            int quantity = foodOrders.get(i).getQuantity();
            int price = foodOrders.get(i).getFood().getPrice() * quantity;
            FoodOrderResponseDto responseDto = new FoodOrderResponseDto(name, quantity, price);
            foodOrderResponseDtos.add(responseDto);
        }

        // OrderResponseDto 만들기
        Restaurant restaurant = restaurantRepository.findById(ordering.getRestaurantId())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 식당입니다."));
        String restaurantName = restaurant.getName();
        int deliveryFee = restaurant.getDeliveryFee();
        int totalPrice = 0;
        totalPrice += deliveryFee;
        for (FoodOrderResponseDto foodOrderResponseDto : foodOrderResponseDtos) {
            totalPrice += foodOrderResponseDto.getPrice();
        }

        // 찍히는지 확인하려고 잠깐 적은 것
        // int min = restaurant.getMinOrderPrice();

        // 최소주문 가격 체크
        if (totalPrice<restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("최소주문가격 이상으로 주문해주세요.");
        }

        // 주문번호 저장
        orderRepository.save(ordering);
        //모든 foodOrder 저장
        foodOrderRepository.saveAll(foodOrders);

         OrderResponseDto orderResponseDto = new OrderResponseDto(
                restaurantName,
                foodOrderResponseDtos,
                deliveryFee,
                totalPrice);


        return orderResponseDto;
    }

    public List<OrderResponseDto> getAllOrders() {
        List<Ordering> orderings = orderRepository.findAll();
        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();
        for (Ordering ordering : orderings) {
            // 오더에 대한 FoodOrder 다 가져오기
            List<FoodOrder> foodOrders = foodOrderRepository.findAllByOrdering(ordering);

            // 가져온 FoodOrder로 FoodOrderResponseDto 만들기
            List<FoodOrderResponseDto> foodOrderResponseDtos = new ArrayList<>();
            for (int i=0; i<foodOrders.size(); i++) {
                String name = foodOrders.get(i).getFood().getName();
                int quantity = foodOrders.get(i).getQuantity();
                int price = foodOrders.get(i).getFood().getPrice() * quantity;
                FoodOrderResponseDto responseDto = new FoodOrderResponseDto(name, quantity, price);
                foodOrderResponseDtos.add(responseDto);
            }

            // OrderResponseDto 만들기
            Restaurant restaurant = restaurantRepository.findById(ordering.getRestaurantId())
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

            orderResponseDtos.add(orderResponseDto);
        }

        return orderResponseDtos;
    }
}
