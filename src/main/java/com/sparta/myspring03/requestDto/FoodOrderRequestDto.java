package com.sparta.myspring03.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FoodOrderRequestDto {

    private Long id;
    private int quantity;

}
