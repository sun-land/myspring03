package com.sparta.myspring03.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderingDto {
    private String restaurantName;
    private int deliveryFee;
    private int totalPrice;
}
