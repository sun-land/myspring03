package com.sparta.myspring03.model;

import com.sparta.myspring03.requestDto.FoodOrderRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class FoodOrder {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name="ORDER_ID", nullable = false)
    private Ordering ordering;

    @ManyToOne
    @JoinColumn(name="FOOD_ID", nullable = false)
    private Food food;

    @Column(nullable = false)
    private int quantity;

    public FoodOrder(FoodOrderRequestDto foodOrderRequestDto) {
        this.ordering = foodOrderRequestDto.getOrdering();
        this.food = foodOrderRequestDto.getFood();
        this.quantity = foodOrderRequestDto.getQuantity();
    }
}
