package com.sparta.myspring03.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FoodRequestDto {

    private Long restaurantId;
    private String name;
    private int price;
}
