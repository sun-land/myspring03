package com.sparta.myspring03.repository;

import com.sparta.myspring03.model.FoodOrder;
import com.sparta.myspring03.model.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodOrderRepository extends JpaRepository<FoodOrder,Long> {
    List<FoodOrder> findAllByOrdering(Ordering ordering);
}
