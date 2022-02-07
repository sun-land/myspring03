package com.sparta.myspring03.requestDto;

import com.sparta.myspring03.model.Food;
import com.sparta.myspring03.model.Ordering;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@AllArgsConstructor
@Getter
public class FoodOrderRequestDto {

    private Ordering ordering;
    private Long id;
    private Food food;
    private int quantity;

}
