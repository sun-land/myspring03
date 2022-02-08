package com.sparta.myspring03.service;

import com.sparta.myspring03.Valid.RestaurantValid;
import com.sparta.myspring03.requestDto.RestaurantRequestDto;
import com.sparta.myspring03.model.Restaurant;
import com.sparta.myspring03.repository.RestaurantRepository;
import com.sparta.myspring03.responseDto.RestaurantResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<RestaurantResponseDto> getAllRestaurants() {

        // 모든 레스토랑 찾아오기
        List<Restaurant> foundRestaurantList = restaurantRepository.findAll();

        // 레스토랑 ResponseDto 만들기
        List<RestaurantResponseDto> responseDtoList = new ArrayList<>();
        for(Restaurant restaurant : foundRestaurantList) {
            RestaurantResponseDto responseDto = new RestaurantResponseDto();
            responseDto.setId(restaurant.getId());
            responseDto.setName(restaurant.getName());
            responseDto.setMinOrderPrice(restaurant.getMinOrderPrice());
            responseDto.setDeliveryFee(restaurant.getDeliveryFee());
            responseDtoList.add(responseDto);
        }
        return responseDtoList;
    }
}
