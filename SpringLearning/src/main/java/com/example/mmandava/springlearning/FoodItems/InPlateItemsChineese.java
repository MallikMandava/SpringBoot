package com.example.mmandava.springlearning.FoodItems;

import java.util.ArrayList;
import java.util.List;

public class InPlateItemsChineese implements  Items{
    @Override
    public List<String> GetItemsList() {
        List<String> plist = new ArrayList<String>();
        plist.add("xys");
        plist.add("Sadsfmbar");
        plist.add("Radfkdjsam");
        plist.add("Cdfkdhutney");
        plist.add("Cujdkfjrd");
        plist.add("Bdjfkdjendi Fry");

        return plist;
    }
}
