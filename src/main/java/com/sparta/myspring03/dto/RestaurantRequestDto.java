package com.sparta.myspring03.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor // 이거 없으니까 서버 에러
@Getter
public class RestaurantRequestDto {

    private String name;
    private int minOrderPrice;
    private int deliveryFee;

}
