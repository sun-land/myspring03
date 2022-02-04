package com.sparta.myspring03.repository;

import com.sparta.myspring03.dto.FoodResponseDto;
import com.sparta.myspring03.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findAllByRestaurantId(Long restaurantId);


    Optional<Food> findByNameAndRestaurantId(String name, Long restaurantId);
}
