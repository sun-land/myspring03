package com.sparta.myspring03.requestDto;

import lombok.Getter;

@Getter
public class RestaurantRequestDto {

    // 입력 받는 값
    private String name;
    // 입력 받는 값
    private int minOrderPrice;
    // 입력 받는 값
    private int deliveryFee;

}
