package com.sparta.myspring03.responseDto;

import com.sparta.myspring03.model.Food;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FoodResponseDto {

    private Long id;
    private String name;
    private int price;

}
