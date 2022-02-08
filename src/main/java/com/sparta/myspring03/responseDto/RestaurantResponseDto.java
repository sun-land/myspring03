package com.sparta.myspring03.responseDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private int minOrderPrice;
    private int DeliveryFee;

}
