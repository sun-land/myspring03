package com.sparta.myspring03.requestDto;

import com.sparta.myspring03.model.FoodOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderRequestDto {
    // 입력받는 값
    private Long restaurantId;
    // 입력받는 값
    private List<FoodOrderRequestDto> foods;
    private String restaurantName;
    private int deliveryFee;
    private int totalPrice;
}
