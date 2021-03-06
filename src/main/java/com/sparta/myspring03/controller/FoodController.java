package com.sparta.myspring03.controller;

import com.sparta.myspring03.model.Food;
import com.sparta.myspring03.requestDto.FoodRequestDto;
import com.sparta.myspring03.responseDto.FoodResponseDto;
import com.sparta.myspring03.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    // 음식 등록 API
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFood(@RequestBody List<FoodRequestDto> foodRequestDtos, @PathVariable Long restaurantId) {
        foodService.registerFood(foodRequestDtos,restaurantId);
    }

    // 메뉴판 조회 API
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getMenu(@PathVariable Long restaurantId) {
        return foodService.getMenu(restaurantId);
    }

}
