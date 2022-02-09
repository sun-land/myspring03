package com.sparta.myspring03.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.myspring03.dto.OrderingDto;
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
    private List<FoodOrder> foods = new ArrayList<>();

    @Column(nullable = false)
    private int deliveryFee;

    @Column(nullable = false)
    private int totalPrice;

    // 생성자
    public Ordering(OrderingDto orderingDto) {
        this.restaurantName = orderingDto.getRestaurantName();
        this.deliveryFee = orderingDto.getDeliveryFee();
        this.totalPrice = orderingDto.getTotalPrice();
    }

    // 연관관계 맺기
    public void addFoodOrderList(List<FoodOrder> foods) {
        for(FoodOrder foodOrder : foods) {
            foodOrder.setOrdering(this);
        }
        this.foods = foods;
    }
}
