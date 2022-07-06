package com.mmandava.samples.onlycomparator;

import com.mmandava.samples.onlycomparator.enhancedsort;

import java.util.*;

public class SimpleList {

    public static void main(String[] args) {
//        List<Integer> values = new ArrayList<>();
        List <Integer> values = new ArrayList<>();

        values.add(1);
        values.add(10);
        values.add(90);
        values.add(34);
        values.add(56);
        values.add(1);
        values.add(10);
        values.add(90);
        values.add(34);
        values.add(56);

        values.add(2,1000);

        Collections.sort(values);

        System.out.println("The value at the index is " +values.get(3));
        System.out.println("The capacity os List is " +  values.size());

        values.forEach(System.out::println); // Stream API ... Lambda Expression

        for (int i : values)
        {
            System.out.println("The numbers are " + i);
        }
      //  values.add("Mallik");
        System.out.println("The values are " + values);

        Vector<Integer> myNUmber = new Vector<Integer>();

        myNUmber.add(103);
        myNUmber.add(909);
        myNUmber.add(804);
        myNUmber.add(172);
        myNUmber.add(151);
        myNUmber.add(108);
        myNUmber.add(906);
        myNUmber.add(805);
        myNUmber.add(173);
        myNUmber.add(150);
        myNUmber.add(152);


        System.out.println("The capacity is " + myNUmber.capacity());

        for(int i : myNUmber)
        {
            System.out.println("The numbers in vector is " +  i);
        }

        Comparator<Integer> com = new enhancedsort();
        Collections.sort(myNUmber,com);

      //  Collections.sort(myNUmber,);
        myNUmber.forEach(i -> System.out.println(i));




    }




}
