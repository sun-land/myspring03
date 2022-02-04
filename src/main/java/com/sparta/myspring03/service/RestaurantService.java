package com.sparta.myspring03.service;

import com.sparta.myspring03.Valid.RestaurantValid;
import com.sparta.myspring03.dto.RestaurantRequestDto;
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
        if (!restaurantValid.isValidMinOrderPrice(restaurantRequestDto.getMinOrderPrice())) {
            throw new IllegalArgumentException("최소주문가격이 조건에 맞지 않습니다.");
        } else if (!restaurantValid.isValidDeliveryFee(restaurantRequestDto.getDeliveryFee())) {
            throw new IllegalArgumentException("기본 배달비가 조건에 맞지 않습니다.");
        } else {
            Restaurant restaurant = new Restaurant(restaurantRequestDto);
            restaurantRepository.save(restaurant);
            return restaurant;
        }
    }

    // 모든 음식점 조회
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
}
