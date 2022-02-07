package com.sparta.myspring03.service;

import com.sparta.myspring03.Valid.RestaurantValid;
import com.sparta.myspring03.requestDto.RestaurantRequestDto;
import com.sparta.myspring03.model.Restaurant;
import com.sparta.myspring03.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // 음식점 등록
    public Restaurant registerRestaurant(RestaurantRequestDto restaurantRequestDto) {
        RestaurantValid restaurantValid = new RestaurantValid();

        // 음식점 유효성 검사
        restaurantValid.isValidRestaurant(restaurantRequestDto);

        // 등록
        Restaurant restaurant = new Restaurant(restaurantRequestDto);
        return restaurantRepository.save(restaurant);
    }

    // 모든 음식점 조회
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
}
