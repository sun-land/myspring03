package com.sparta.myspring03.Valid;

import com.sparta.myspring03.requestDto.RestaurantRequestDto;

public class RestaurantValid {

    // 최소주문가격 조건
    public boolean isValidMinOrderPrice(int minOrderPrice) {
        return minOrderPrice>=1000&&minOrderPrice<=100000&&minOrderPrice%100==0;
    }

    // 배달비 조건
    public boolean isValidDeliveryFee(int deliveryFee) {
        return deliveryFee>=0&&deliveryFee<=10000&&deliveryFee%500==0;
    }

    // 음식적 등록 유효성 검사
    public void isValidRestaurant(RestaurantRequestDto restaurantRequestDto) {
        if (!isValidMinOrderPrice(restaurantRequestDto.getMinOrderPrice())) {
            throw new IllegalArgumentException("최소주문가격이 조건에 맞지 않습니다.");
        } else if (!isValidDeliveryFee(restaurantRequestDto.getDeliveryFee())) {
            throw new IllegalArgumentException("기본 배달비가 조건에 맞지 않습니다.");
        }
    }
}
