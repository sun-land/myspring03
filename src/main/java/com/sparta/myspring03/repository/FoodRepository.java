package com.sparta.myspring03.repository;

import com.sparta.myspring03.model.Food;
import com.sparta.myspring03.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findAllByRestaurant(Restaurant restaurant);
}
