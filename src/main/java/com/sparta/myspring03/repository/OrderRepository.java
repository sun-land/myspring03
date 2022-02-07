package com.sparta.myspring03.repository;

import com.sparta.myspring03.model.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Ordering,Long> {
}
