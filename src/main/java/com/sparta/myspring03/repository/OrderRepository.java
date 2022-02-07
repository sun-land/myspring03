package com.sparta.myspring03.repository;

import com.sparta.myspring03.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
