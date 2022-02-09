package com.sparta.myspring03.dto;

import com.sparta.myspring03.model.Ordering;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class FoodOrderDto {
    private String name;
    private int quantity;
    private int pirce;
}
