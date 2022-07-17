package com.example.mmandava.springlearning.FoodItems;

import java.util.ArrayList;
import java.util.List;

public class InPlateItemsIndian implements  Items {
    @Override
    public List<String> GetItemsList() {
        List<String> plist = new ArrayList<String>();
        plist.add("Dal");
        plist.add("Sambar");
        plist.add("Rasam");
        plist.add("Chutney");
        plist.add("Curd");
        plist.add("Bendi Fry");

        return plist;
    }
}
