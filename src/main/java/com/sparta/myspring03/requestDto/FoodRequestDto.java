package com.sparta.myspring03.requestDto;

import com.sparta.myspring03.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FoodRequestDto {

    private Restaurant restaurant;
    // 입력 받는 값
    private String name;
    // 입력 받는 값
    private int price;
}
