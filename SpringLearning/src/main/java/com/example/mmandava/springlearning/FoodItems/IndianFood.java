package com.example.mmandava.springlearning.FoodItems;

import java.util.ArrayList;
import java.util.List;

public class IndianFood  implements  Food{
    private InPlateItemsIndian plateintems;

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    private String hotelName;

    public IndianFood() {
    }

    public IndianFood(InPlateItemsIndian arg1) {
        plateintems = arg1;
    }

    public void setPlateintems(InPlateItemsIndian plateintems) {
        System.out.println("Setting the Argument");
        this.plateintems = plateintems;
    }

    public String GetFood() {
        return "Meals";
    }

    @Override
    public List<String> GetPlateItems() {
        return plateintems.GetItemsList();
    }


}
