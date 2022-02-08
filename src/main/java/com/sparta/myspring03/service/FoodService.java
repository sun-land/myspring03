package com.sparta.myspring03.service;

import com.sparta.myspring03.Valid.FoodValid;
import com.sparta.myspring03.model.Restaurant;
import com.sparta.myspring03.repository.RestaurantRepository;
import com.sparta.myspring03.requestDto.FoodRequestDto;
import com.sparta.myspring03.responseDto.FoodResponseDto;
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
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository, RestaurantRepository restaurantRepository) {
        this.foodRepository = foodRepository;
        this.restaurantRepository = restaurantRepository;
    }

    // 음식 등록 메소드
    public void registerFood(List<FoodRequestDto> foodRequestDtos, Long restaurantId) {

        FoodValid foodValid = new FoodValid();

        // 레스토랑 존재 체크
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 음식점입니다."));


        // 작성한 메뉴끼리 중복 체크, 가격 체크
        foodValid.isValidFood(foodRequestDtos);

        // 등록한 음식과 중복 체크
        List<Food> existFoods = foodRepository.findAllByRestaurant(restaurant);
        List<String> existFoodNames = new ArrayList<>();
        for (Food existFood : existFoods) {
            existFoodNames.add(existFood.getName());
        }
        foodValid.isDuplicatedFood(foodRequestDtos,existFoodNames);


        // foods 리스트 만들기
        List<Food> foods = new ArrayList<>();
        for (FoodRequestDto requestDto : foodRequestDtos) {
            requestDto.setRestaurant(restaurant);
            Food food = new Food(requestDto);
            foods.add(food);
        }

        // 디비에 저장
        foodRepository.saveAll(foods);
    }

    // 메뉴판 조회하기 메소드
    public List<FoodResponseDto> getAllFoods(Long restaurantId) {

        // 레스토랑 존재 확인
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 가게입니다."));
        List<Food> existFoods = foodRepository.findAllByRestaurant(restaurant);

        // 음식 리스트 만들기
        List<FoodResponseDto> foods = new ArrayList<>();
        for (Food food : existFoods) {
            FoodResponseDto foodResponseDto = new FoodResponseDto(food);
            foods.add(foodResponseDto);
        }
        return foods;
    }
}
