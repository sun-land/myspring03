package com.sparta.myspring03.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.myspring03.requestDto.OrderRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Ordering {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String restaurantName;

    @JsonManagedReference
    @OneToMany(mappedBy = "ordering")
    private List<FoodOrder> foodOrderList = new ArrayList<>();

    @Column(nullable = false)
    private int deliveryFee;

    @Column(nullable = false)
    private int totalPrice;

    public Ordering(OrderRequestDto orderRequestDto) {
        this.restaurantName = orderRequestDto.getRestaurantName();
        this.deliveryFee = orderRequestDto.getDeliveryFee();
        this.totalPrice = orderRequestDto.getTotalPrice();
    }
}
