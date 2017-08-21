package com.example.hulya.yemekhane.viewmodel;

import java.util.ArrayList;

/**
 * Created by hulya on 14.08.2017.
 */

public class FoodListVM extends ArrayList<FoodListVM> {

    private String foodType;
    private String foodName1;
    private String foodName2;
    private String foodName3;
    private int foodImageLink1;
    private int foodImageLink2;
    private int foodImageLink3;

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

    public int getFoodImageLink1() {
        return foodImageLink1;
    }

    public void setFoodImageLink1(int foodImageLink1) {
        this.foodImageLink1 = foodImageLink1;
    }

    public int getFoodImageLink2() {
        return foodImageLink2;
    }

    public void setFoodImageLink2(int foodImageLink2) {
        this.foodImageLink2 = foodImageLink2;
    }

    public int getFoodImageLink3() {
        return foodImageLink3;
    }

    public void setFoodImageLink3(int foodImageLink3) {
        this.foodImageLink3 = foodImageLink3;
    }

}
