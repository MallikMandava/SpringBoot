package com.mmandava.samples.compvscompl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Runner {

    public static void main(String[] args) {
        List<Laptop>  laps = new ArrayList<Laptop>();
        laps.add(new Laptop(20,"Dell" , 8));
        laps.add(new Laptop(30,"Acer" , 16));
        laps.add(new Laptop(40,"Hp" , 24));
        laps.add(new Laptop(10,"Mac" , 32));
        laps.add(new Laptop(10,"Dell" , 32));

        Collections.sort(laps);

        laps.forEach(System.out::println);

        Comparator<Laptop> com = new newcomp();
        Collections.sort(laps,com);

        for( Laptop lap : laps)
        {
            System.out.println(lap);
        }


    }
}
