package com.sparta.myspring03.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class RestaurantRequestDto {

    private String name;
    private int minOrderPrice;
    private int deliveryFee;

}
