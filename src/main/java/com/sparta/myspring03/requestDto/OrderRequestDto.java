package com.sparta.myspring03.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class OrderRequestDto {

    private Long restaurantId;
    private List<FoodOrderRequestDto> foods;

}
