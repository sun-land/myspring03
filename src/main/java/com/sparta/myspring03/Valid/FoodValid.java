package com.sparta.myspring03.Valid;

import com.sparta.myspring03.dto.FoodRequestDto;

import java.util.ArrayList;
import java.util.List;

public class FoodValid {
    public boolean isValidPrice(int price) {
        return price>=100&&price<=1000000&&price%100==0;
    }

    public boolean isDuplicateName(List<FoodRequestDto> foodRequestDtos) {
        List<String> names = new ArrayList<>();

        for (FoodRequestDto requestDto:foodRequestDtos) {
            if (!names.contains(requestDto.getName())) {
                names.add(requestDto.getName());
            } else {
                return false;
            }
        }
        return true;
    }
}
