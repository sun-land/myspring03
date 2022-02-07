package com.sparta.myspring03.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderResponseDto {

    private String restaurantName;
    private List<FoodOrderResponseDto> foods;
    private int deliveryFee;
    private int totalPrice;
}
