package com.sparta.myspring03.model;

import com.sparta.myspring03.requestDto.FoodRequestDto;
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

    @ManyToOne
    @JoinColumn(name="RESTAURNAT_ID", nullable = false)
    Restaurant restaurant;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    public Food(FoodRequestDto foodRequestDto) {
        this.restaurant = foodRequestDto.getRestaurant();
        this.name = foodRequestDto.getName();
        this.price = foodRequestDto.getPrice();
    }


}
