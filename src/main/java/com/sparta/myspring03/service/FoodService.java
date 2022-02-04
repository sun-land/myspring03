package com.sparta.myspring03.service;

import com.sparta.myspring03.dto.FoodRequestDto;
import com.sparta.myspring03.dto.FoodResponseDto;
import com.sparta.myspring03.model.Food;
import com.sparta.myspring03.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<FoodResponseDto> getAllFoods(Long restaurantId) {
        List<Food> realFoods = foodRepository.findAllByRestaurantId(restaurantId);
        List<FoodResponseDto> foods = new ArrayList<FoodResponseDto>();
        for (Food food : realFoods) {
            Long id = food.getId();
            String name = food.getName();
            int price = food.getPrice();
            FoodResponseDto foodResponseDto = new FoodResponseDto(id, name, price);
            foods.add(foodResponseDto);
        }
        return foods;
    }
}
