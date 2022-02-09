package com.sparta.myspring03.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.myspring03.requestDto.RestaurantRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Restaurant {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;

    @JsonManagedReference
    @OneToMany(mappedBy = "restaurant")
    private List<Food> foods;

    @Column(nullable = false)
    private int x;

    @Column(nullable = false)
    private int y;

    public Restaurant(RestaurantRequestDto restaurantRequestDto) {
        this.name = restaurantRequestDto.getName();
        this.minOrderPrice = restaurantRequestDto.getMinOrderPrice();
        this.deliveryFee = restaurantRequestDto.getDeliveryFee();
        this.x = restaurantRequestDto.getX();
        this.y = restaurantRequestDto.getY();
    }

}
