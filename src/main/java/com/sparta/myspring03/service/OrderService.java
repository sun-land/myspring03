package com.sparta.myspring03.service;

import com.sparta.myspring03.Valid.OrderValid;
import com.sparta.myspring03.dto.FoodOrderDto;
import com.sparta.myspring03.dto.OrderingDto;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final FoodOrderRepository foodOrderRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    // 모든 주문 조회하기
    public List<Ordering> getAllOrders() {
        return orderRepository.findAll();

    }

    // 주문 저장하기
    public Ordering saveOrder(OrderRequestDto requestDto) {

        // 레스토랑의 정보
        Long restaurantId = requestDto.getRestaurantId();
        Restaurant foundRestaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(()-> new IllegalArgumentException("등록되지 않은 가게입니다."));

        // 음식의 정보
        List<FoodOrderRequestDto> foodOrderRequestDtoList = requestDto.getFoods();
        List<Long> foodIds = getFoodIds(foodOrderRequestDtoList);
        List<Food> foundFoodList = foodRepository.findAllById(foodIds);

        // 1. 유효성 체크
        OrderValid orderValid = new OrderValid();
        orderValid.isValidFoodList(foodIds,foundFoodList);
        orderValid.isValidQuantity(foodOrderRequestDtoList);
        int foodTotalPrice = orderValid.isValidTotalPrice(foodOrderRequestDtoList,foundFoodList,foundRestaurant);

        // 2. FoodOrder 저장
        List<FoodOrder> foodOrderList = saveFoodOrder(foundFoodList,foodOrderRequestDtoList);

        // 3. Order 저장
        return saveOrdering(foundRestaurant,foodTotalPrice,foodOrderList);
    }


    // 아이디 리스트 만들기 메소드
    private List<Long> getFoodIds (List<FoodOrderRequestDto> foodOrderRequestDtoList) {
        List<Long> foodIds = new ArrayList<>();
        for(FoodOrderRequestDto dto : foodOrderRequestDtoList) {
            foodIds.add(dto.getId());
        }
        return foodIds;
    }

    // FoodOrder 리스트 저장 메소드
    private List<FoodOrder> saveFoodOrder(List<Food> foundFoodList, List<FoodOrderRequestDto> foodOrderRequestDtoList) {
        List<FoodOrder> foodOrderList = new ArrayList<>();
        for (int i=0; i<foundFoodList.size();i++) {
            String name = foundFoodList.get(i).getName();
            int quantity = foodOrderRequestDtoList.get(i).getQuantity();
            int price = foundFoodList.get(i).getPrice() * quantity;
            FoodOrderDto foodOrderDto = new FoodOrderDto(name, quantity, price);
            FoodOrder foodOrder = new FoodOrder(foodOrderDto);
            foodOrderList.add(foodOrder);
        }
        return foodOrderRepository.saveAll(foodOrderList);
    }

    // Order 저장 메소드
    private Ordering saveOrdering(Restaurant foundRestaurant, int foodTotalPrice, List<FoodOrder> foodOrderList) {
        String restaurantName = foundRestaurant.getName();
        int deliveryFee = foundRestaurant.getDeliveryFee();
        int totalPrice = foodTotalPrice + deliveryFee;
        OrderingDto orderingDto = new OrderingDto(restaurantName,deliveryFee,totalPrice);
        Ordering ordering = new Ordering(orderingDto);
        ordering.addFoodOrderList(foodOrderList);
        return orderRepository.save(ordering);
    }
}
