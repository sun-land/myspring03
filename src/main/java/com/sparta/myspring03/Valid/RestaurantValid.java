package com.sparta.myspring03.Valid;

public class RestaurantValid {
    public boolean isValidMinOrderPrice(int minOrderPrice) {
        return minOrderPrice>=1000&&minOrderPrice<=100000&&minOrderPrice%100==0;
    }

    public boolean isValidDeliveryFee(int deliveryFee) {
        return deliveryFee>=0&&deliveryFee<=10000&&deliveryFee%500==0;
    }
}
