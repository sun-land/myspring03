package com.sparta.myspring03.responseDto;

import com.sparta.myspring03.model.FoodOrder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// 아직 안씀
@Setter
@Getter
public class OrderResponseDto {
    private String restaurantName;
    private List<FoodOrder> foods;
    private int deliveryFee;
    private int totalPrice;
}
