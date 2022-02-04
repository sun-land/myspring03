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

    public void registerFood(List<FoodRequestDto> foodRequestDtos, Long restaurantId) {
        FoodValid foodValid = new FoodValid();

        // 작성한 메뉴 중 중복이름 체크
        if(!foodValid.isDuplicateName(foodRequestDtos)) {
            throw new IllegalArgumentException("작성한 메뉴 중에 동일한 이름이 존재합니다.");
        }

        // 가격 조건, 이미 있는 이름 체크
        for (FoodRequestDto requestDto : foodRequestDtos) {
            Optional<Food> find = foodRepository.findByNameAndRestaurantId(requestDto.getName(),restaurantId);
            if (!foodValid.isValidPrice(requestDto.getPrice())) {
                throw new IllegalArgumentException("가격이 조건에 맞지 않습니다.");
            } else if (find.isPresent()) {
                throw new IllegalArgumentException("동일한 이름의 음식이 존재합니다.");
            }
        }

        // 저장
        for (FoodRequestDto requestDto : foodRequestDtos) {
            requestDto.setRestaurantId(restaurantId);
            Food food = new Food(requestDto);
            foodRepository.save(food);
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
