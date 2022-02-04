package com.sparta.myspring03.controller;

import com.sparta.myspring03.dto.FoodRequestDto;
import com.sparta.myspring03.model.Food;
import com.sparta.myspring03.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    // 음식 등록 API
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public Food registerFood(@RequestBody FoodRequestDto foodRequestDto, @PathVariable Long restaurantId) {
        return foodService.registerFood(foodRequestDto,restaurantId);
    }

}
