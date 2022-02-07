package com.sparta.myspring03.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FoodOrderResponseDto {

    private String name;
    private int quantity;
    private int price;


}
