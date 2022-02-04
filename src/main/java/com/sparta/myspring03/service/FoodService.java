package com.sparta.myspring03.service;

import com.sparta.myspring03.Valid.FoodValid;
import com.sparta.myspring03.dto.FoodRequestDto;
import com.sparta.myspring03.dto.FoodResponseDto;
import com.sparta.myspring03.model.Food;
import com.sparta.myspring03.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<Food> registerFood(List<FoodRequestDto> foodRequestDtos, Long restaurantId) {
        List<Food> foods = new ArrayList<>();
        for (FoodRequestDto requestDto : foodRequestDtos) {
            requestDto.setRestaurantId(restaurantId);
            Food food = new Food(requestDto);
            foodRepository.save(food);
            foods.add(food);
        }
        return foods;
    }

    public List<FoodResponseDto> getAllFoods(Long restaurantId) {
        List<Food> realFoods = foodRepository.findAllByRestaurantId(restaurantId);
        List<FoodResponseDto> foods = new ArrayList<>();
        for (Food food : realFoods) {
            FoodResponseDto foodResponseDto = new FoodResponseDto(food);
            foods.add(foodResponseDto);
        }
        return foods;
    }
}
