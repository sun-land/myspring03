package com.sparta.myspring03.Valid;

import com.sparta.myspring03.model.Food;
import com.sparta.myspring03.model.Restaurant;
import com.sparta.myspring03.requestDto.FoodOrderRequestDto;

import java.util.List;

public class OrderValid {

    // 메뉴 존재하는지 체크
    public void isValidFoodList(List<Long> foodIds, List<Food> foundFoodList) {
        if (foodIds.size() != foundFoodList.size()) {
            throw new IllegalArgumentException("주문에 유효하지 않은 메뉴가 포함되어 있습니다.");
        }
    }

    // 주문 수량 1~100 체크
    public void isValidQuantity(List<FoodOrderRequestDto> foodOrderRequestDtoList) {
        for (FoodOrderRequestDto dto : foodOrderRequestDtoList) {
            int quantity = dto.getQuantity();
            if (quantity<1 || quantity>100) {
                throw new IllegalArgumentException("주문 수량은 1이상 100이하로 입력해주세요.");
            }
        }
    }

    // 최소주문가격 체크
    public void isValidTotalPrice(
            List<FoodOrderRequestDto> foodOrderRequestDtoList,
            List<Food> foundFoodList,
            Restaurant foundRestaurant
    ) {
        int foodTotalPrice = 0;
        for (int i = 0; i< foundFoodList.size(); i++) {
            int onePrice = foundFoodList.get(i).getPrice();
            int quantity = foodOrderRequestDtoList.get(i).getQuantity();
            foodTotalPrice += onePrice*quantity;
        }
        if(foodTotalPrice< foundRestaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("최소주문가격 이상으로 주문해주세요.");
        }
    }
}
