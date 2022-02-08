package com.sparta.myspring03.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int price;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="ORDER_ID", nullable = false)
    private Ordering ordering;

    public FoodOrder(FoodOrderRequestDto foodOrderRequestDto) {
        this.name = foodOrderRequestDto.getName();
        this.quantity = foodOrderRequestDto.getQuantity();
        this.price = foodOrderRequestDto.getPirce();
        this.ordering = foodOrderRequestDto.getOrdering();
    }
}
