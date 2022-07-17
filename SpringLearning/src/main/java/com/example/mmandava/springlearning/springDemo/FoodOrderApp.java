package com.example.mmandava.springlearning.springDemo;

import com.example.mmandava.springlearning.FoodItems.ChineeseFood;
import com.example.mmandava.springlearning.FoodItems.Food;
import com.example.mmandava.springlearning.FoodItems.IndianFood;

public class FoodOrderApp {

    public static void main(String[] args) {
//        Food food = new IndianFood();
        Food food = new ChineeseFood();
        System.out.println(food.GetFood());


    }

}
