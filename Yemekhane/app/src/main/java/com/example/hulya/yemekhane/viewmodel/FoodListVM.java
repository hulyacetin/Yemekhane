package com.example.hulya.yemekhane.viewmodel;

import java.io.Serializable;

/**
 * Created by hulya on 14.08.2017.
 */

public class FoodListVM implements Serializable {

    private String foodType;
    private String foodName1;
    private String foodName2;
    private String foodName3;
    private String foodNetworkImageLink1;
    private String foodNetworkImageLink2;
    private String foodNetworkImageLink3;

    public FoodListVM() {

    }
    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFoodName1() {
        return foodName1;
    }

    public void setFoodName1(String foodName1) {
        this.foodName1 = foodName1;
    }

    public String getFoodName2() {
        return foodName2;
    }

    public void setFoodName2(String foodName2) {
        this.foodName2 = foodName2;
    }

    public String getFoodName3() {
        return foodName3;
    }

    public void setFoodName3(String foodName3) {
        this.foodName3 = foodName3;
    }


    public String getFoodNetworkImageLink1() {
        return foodNetworkImageLink1;
    }

    public void setFoodNetworkImageLink1(String foodNetworkImageLink1) {
        this.foodNetworkImageLink1 = foodNetworkImageLink1;
    }

    public String getFoodNetworkImageLink2() {
        return foodNetworkImageLink2;
    }

    public void setFoodNetworkImageLink2(String foodNetworkImageLink2) {
        this.foodNetworkImageLink2 = foodNetworkImageLink2;
    }

    public String getFoodNetworkImageLink3() {
        return foodNetworkImageLink3;
    }

    public void setFoodNetworkImageLink3(String foodNetworkImageLink3) {
        this.foodNetworkImageLink3 = foodNetworkImageLink3;
    }
}

