package com.sparta.myspring03.requestDto;

import com.sparta.myspring03.model.FoodOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class OrderRequestDto {
    private Long restaurantId;
    private List<FoodOrderRequestDto> foods;
}
