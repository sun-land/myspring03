package com.sparta.myspring03.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.myspring03.dto.FoodOrderDto;
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
    @JoinColumn(name="ORDER_ID")
    private Ordering ordering;

    // 생성자
    public FoodOrder(FoodOrderDto foodOrderDto) {
        this.name = foodOrderDto.getName();
        this.quantity = foodOrderDto.getQuantity();
        this.price = foodOrderDto.getPirce();
    }

    // ordering Setter
    public void setOrdering(Ordering ordering) {
        this.ordering = ordering;
    }
}
