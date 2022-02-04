package com.sparta.myspring03.repository;

import com.sparta.myspring03.dto.FoodResponseDto;
import com.sparta.myspring03.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findAllByRestaurantId(Long restaurantId);
}
