package com.sparta.myspring03.requestDto;

import com.sparta.myspring03.model.Ordering;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodOrderRequestDto {
    // 입력받는 값 : Food의 아이디
    private Long id;
    // 입력받는 값 : 수량
    private int quantity;
    private String name;
    private int pirce;
    private Ordering ordering;
}
