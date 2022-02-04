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

    public Food registerFood(FoodRequestDto foodRequestDto, Long restaurantId) {
        Optional<Food> find = foodRepository.findByName(foodRequestDto.getName());
        FoodValid foodValid = new FoodValid();
        if (find.isPresent()) {
            throw new IllegalArgumentException("동일한 이름의 음식이 존재합니다.");
        } else if (!foodValid.isValidPrice(foodRequestDto.getPrice())) {
            throw new IllegalArgumentException("가격이 조건에 맞지 않습니다.");
        } else {
            foodRequestDto.setRestaurantId(restaurantId);
            Food food = new Food(foodRequestDto);
            foodRepository.save(food);
            return food;
        }
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
