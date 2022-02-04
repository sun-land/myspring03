package com.sparta.myspring03.repository;

import com.sparta.myspring03.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
