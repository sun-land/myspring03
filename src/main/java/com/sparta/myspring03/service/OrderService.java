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

    // 주문 저장하기
    public Ordering saveOrder(OrderRequestDto orderRequestDto) {

        // 레스토랑 정보 가져오기
        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId())
                .orElseThrow(()-> new IllegalArgumentException("등록되지 않은 가게입니다."));
        String restaurantName = restaurant.getName();
        int deliveryFee = restaurant.getDeliveryFee();
        int minOrderPrice = restaurant.getMinOrderPrice();

        // 주문 수량 유효성 체크
        List<FoodOrderRequestDto> foodOrderRequestDtoList = orderRequestDto.getFoods();
        for (FoodOrderRequestDto requestDto : foodOrderRequestDtoList) {
            if(requestDto.getQuantity()<1 || requestDto.getQuantity()>100) {
                throw new IllegalArgumentException("수량은 1이상 100이하로 입력해주세요.");
            }
        }

        // 주문음식 정보 리스트 가져오기
        List<Long> foodIdList = new ArrayList<>();
        for(FoodOrderRequestDto requestDto : foodOrderRequestDtoList) {
            foodIdList.add(requestDto.getId());
        }
        List<Food> foundFoodList = foodRepository.findAllById(foodIdList);

        // 최소주문가격 미달 체크
        int foodTotalPrice = 0;
        for (int i=0; i<foodOrderRequestDtoList.size();i++) {
            int thisPrice = foundFoodList.get(i).getPrice();
            int thisQuantity = foodOrderRequestDtoList.get(i).getQuantity();
            foodTotalPrice += thisPrice*thisQuantity;
        }
        if (minOrderPrice>foodTotalPrice) {
            throw new IllegalArgumentException("최소 주문 가격 이상으로 주문해주세요");
        }

        // 주문 생성
        int totalPrice = foodTotalPrice + deliveryFee;
        orderRequestDto.setRestaurantName(restaurantName);
        orderRequestDto.setDeliveryFee(deliveryFee);
        orderRequestDto.setTotalPrice(totalPrice);
        Ordering ordering = new Ordering(orderRequestDto);
        Ordering savedOrder = orderRepository.save(ordering);


        // 주문 음식 저장
        List<FoodOrder> foodOrderList = new ArrayList<>();
        for (int i=0; i<foodOrderRequestDtoList.size();i++) {
            foodOrderRequestDtoList.get(i).setName(foundFoodList.get(i).getName());
            foodOrderRequestDtoList.get(i)
                    .setPirce(foundFoodList.get(i).getPrice()*foodOrderRequestDtoList.get(i).getQuantity());
            foodOrderRequestDtoList.get(i).setOrdering(savedOrder);
            FoodOrder foodOrder = new FoodOrder(foodOrderRequestDtoList.get(i));
            foodOrderList.add(foodOrder);
        }
        foodOrderRepository.saveAll(foodOrderList);

        // response 내려주기

        return orderRepository.findById(savedOrder.getId())
                .orElseThrow(()-> new IllegalArgumentException("주문이 저장되지 않았습니다."));
    }

    public List<Ordering> getAllOrders() {
        return orderRepository.findAll();
    }
}
