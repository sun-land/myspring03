package com.sparta.myspring03.model;

import com.sparta.myspring03.dto.FoodRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Food {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long restaurantId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    public Food(FoodRequestDto foodRequestDto) {
        this.restaurantId = foodRequestDto.getRestaurantId();
        this.name = foodRequestDto.getName();
        this.price = foodRequestDto.getPrice();
    }


}
