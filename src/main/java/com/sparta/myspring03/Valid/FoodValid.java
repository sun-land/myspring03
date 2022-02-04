package com.sparta.myspring03.Valid;

public class FoodValid {
    public boolean isValidPrice(int price) {
        return price>=100&&price<=1000000&&price%100==0;
    }
}
