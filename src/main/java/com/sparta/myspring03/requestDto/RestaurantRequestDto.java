package com.sparta.myspring03.requestDto;

import lombok.Getter;

@Getter
public class RestaurantRequestDto {

    private String name;
    private int minOrderPrice;
    private int deliveryFee;

}
