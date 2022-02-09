package com.sparta.myspring03.controller;

import com.sparta.myspring03.requestDto.RestaurantRequestDto;
import com.sparta.myspring03.model.Restaurant;
import com.sparta.myspring03.responseDto.RestaurantResponseDto;
import com.sparta.myspring03.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    // 음식점 등록 API
    @PostMapping("/restaurant/register")
    public Restaurant registerRestaurant(@RequestBody RestaurantRequestDto restaurantRequestDto) {
        return restaurantService.registerRestaurant(restaurantRequestDto);
    }

    // 모든 음식점 조회 API
    @GetMapping("/restaurants")
    public List<RestaurantResponseDto> getAllRestaurants(@RequestParam int x, @RequestParam int y) {
        return restaurantService.getAllRestaurants(x,y);
    }


}
