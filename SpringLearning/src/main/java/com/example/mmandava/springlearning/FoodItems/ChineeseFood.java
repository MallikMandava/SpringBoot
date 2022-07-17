package com.example.mmandava.springlearning.FoodItems;

import java.util.List;

public class ChineeseFood implements  Food{

    private InPlateItemsChineese chineeseItems;

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    private String hotelName;

    public void setChineeseItems(InPlateItemsChineese chineeseItems) {
        System.out.println("Inside Setter Method");
        this.chineeseItems = chineeseItems;
    }

    @Override
    public String GetFood() {
        return "Chineese Food";
    }

    public ChineeseFood() {

    }
    public ChineeseFood(InPlateItemsChineese chineeseItems) {
        this.chineeseItems = chineeseItems;
    }

    @Override
    public List<String> GetPlateItems() {
        return chineeseItems.GetItemsList();
    }
}
