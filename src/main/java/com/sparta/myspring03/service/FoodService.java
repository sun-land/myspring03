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

        // 레스토랑 찾아오기
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(()-> new IllegalArgumentException("등록되지 않은 가게입니다."));

        // 작성한 메뉴끼리 중복 체크, 가격 체크
        foodValid.isValidFood(foodRequestDtos);

        // 등록한 음식과 중복 체크 1.등록된 식당 이름 리스트 생성
        List<Food> existFoods = foodRepository.findAllByRestaurant(restaurant);
        List<String> existFoodNames = new ArrayList<>();
        for (Food existFood : existFoods) {
            existFoodNames.add(existFood.getName());
        }
        // 등록한 음식과 중복 체크 2.중복 체크
        foodValid.isDuplicatedFood(foodRequestDtos,existFoodNames);


        // food 리스트 디비에 저장
        List<Food> foods = new ArrayList<>();
        for (FoodRequestDto requestDto : foodRequestDtos) {
            requestDto.setRestaurant(restaurant);
            Food food = new Food(requestDto);
            foods.add(food);
        }
        foodRepository.saveAll(foods);
    }

    // 메뉴판 조회하기 메소드
    public List<Food> getMenu(Long restaurantId) {
        // 레스토랑 찾아오기
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(()->new IllegalArgumentException("등록되지 않은 가게입니다."));
        // 레스토랑의 foods 리턴
        return restaurant.getFoods();
    }




}
