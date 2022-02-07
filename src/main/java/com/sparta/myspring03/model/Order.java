package com.sparta.myspring03.model;

import com.sparta.myspring03.requestDto.FoodOrderRequestDto;
import com.sparta.myspring03.requestDto.FoodRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Order {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long restaurantId;

    // 테이블에는 컬렉션 형태를 저장할 수 없다.

}
