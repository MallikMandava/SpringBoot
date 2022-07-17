package com.example.mmandava.springlearning.springDemo;

import com.example.mmandava.springlearning.FoodItems.ChineeseFood;
import com.example.mmandava.springlearning.FoodItems.Food;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Iocfoodapp {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =  new  ClassPathXmlApplicationContext("applicationContext.xml");
       // Food food = context.getBean("foodbean",Food.class);
        ChineeseFood food = context.getBean("foodbean",ChineeseFood.class);
        System.out.println(food.GetFood());

       System.out.println(food.GetPlateItems());
        System.out.println("The HOtel Name is" + food.getHotelName());
       context.close();

    }
}
