package com.sparta.myspring03.requestDto;

import com.sparta.myspring03.model.Ordering;
import lombok.Getter;
import lombok.Setter;

@Getter
public class FoodOrderRequestDto {
    private Long id;
    private int quantity;
}
