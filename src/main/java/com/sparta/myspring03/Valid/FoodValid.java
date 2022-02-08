package com.sparta.myspring03.Valid;

import com.sparta.myspring03.requestDto.FoodRequestDto;

import java.util.ArrayList;
import java.util.List;

public class FoodValid {

    // 가격 조건
    public boolean isValidPrice(int price) {
        return price>=100&&price<=1000000&&price%100==0;
    }

    // 유효성 체크
    public void isValidFood(List<FoodRequestDto> foodRequestDtos) {
        List<String> names = new ArrayList<>();

        for (FoodRequestDto requestDto:foodRequestDtos) {
            if (!isValidPrice(requestDto.getPrice())) {
                throw new IllegalArgumentException("가격이 조건에 맞지 않습니다.");
            } else if (names.contains(requestDto.getName())) {
                throw new IllegalArgumentException("작성한 메뉴 중 동일한 이름의 메뉴가 존재합니다.");
            } else {
                names.add(requestDto.getName());
            }
        }

    }

    // 등록된 음식과 중복 체크
    public void isDuplicatedFood(List<FoodRequestDto> foodRequestDtos, List<String> existFoodNames) {
        for (FoodRequestDto requestDto : foodRequestDtos) {
            if(existFoodNames.contains(requestDto.getName())) {
                throw new IllegalArgumentException("이미 등록된 메뉴 중 동일한 이름의 메뉴가 존재합니다.");
            }
        }
    }
}
