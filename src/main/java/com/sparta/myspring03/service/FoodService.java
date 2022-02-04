package com.sparta.myspring03.service;

import com.sparta.myspring03.dto.FoodRequestDto;
import com.sparta.myspring03.model.Food;
import com.sparta.myspring03.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Food registerFood(FoodRequestDto foodRequestDto, Long restaurantId) {
        foodRequestDto.setRestaurantId(restaurantId);
        Food food = new Food(foodRequestDto);
        foodRepository.save(food);
        return food;
    }
}
