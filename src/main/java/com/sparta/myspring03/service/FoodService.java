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

    // 유효성 체크 부분 일단 다 주석 처리
    public void registerFood(List<FoodRequestDto> foodRequestDtos, Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 음식점입니다."));
        List<Food> foods = new ArrayList<>();
//        FoodValid foodValid = new FoodValid();
//
//        // 작성한 메뉴 중 중복이름 체크
//        if(!foodValid.isDuplicateName(foodRequestDtos)) {
//            throw new IllegalArgumentException("작성한 메뉴 중에 동일한 이름이 존재합니다.");
//        }
//
//        // 가격 조건, 이미 있는 이름 체크
//        for (FoodRequestDto requestDto : foodRequestDtos) {
//            Optional<Food> find = foodRepository.findByNameAndRestaurantId(requestDto.getName(),restaurantId);
//            if (!foodValid.isValidPrice(requestDto.getPrice())) {
//                throw new IllegalArgumentException("가격이 조건에 맞지 않습니다.");
//            } else if (find.isPresent()) {
//                throw new IllegalArgumentException("동일한 이름의 음식이 존재합니다.");
//            }
//        }

        // foods 리스트 만들기
        for (FoodRequestDto requestDto : foodRequestDtos) {
            requestDto.setRestaurant(restaurant);
            Food food = new Food(requestDto);
            foods.add(food);
        }

        foodRepository.saveAll(foods);
    }

    // 메뉴판 조회하기
    public List<FoodResponseDto> getAllFoods(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 가게입니다."));
        List<Food> existFoods = foodRepository.findAllByRestaurant(restaurant);

        List<FoodResponseDto> foods = new ArrayList<>();
        for (Food food : existFoods) {
            FoodResponseDto foodResponseDto = new FoodResponseDto(food);
            foods.add(foodResponseDto);
        }
        return foods;
    }
}
