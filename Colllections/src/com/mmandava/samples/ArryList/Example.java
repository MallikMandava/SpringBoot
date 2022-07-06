package com.mmandava.samples.ArryList;


import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Example {
    public static void main(String[] args) {

        // ArrayList is Ordered or Indexed
        // ArrayList Virtual capacity is 10
        ArrayList<String> nameslist = new ArrayList<String>();
        nameslist.add("Mallik");
        nameslist.add("Gayathri");
        nameslist.add("Abhiram");
        nameslist.add("Ananya");
        System.out.println("The list is " + nameslist);


        ArrayList<Integer> numberList = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        System.out.println("The values are = " + numberList);

        // How to remove element in ArrayList

        ArrayList<Integer> nums = new ArrayList<Integer>();
        nums.add(1);
        nums.add(3);
        nums.add(4);
        nums.add(7);
        nums.add(8);

        System.out.println("Before Remove Values of num is " + nums);
        nums.remove(3); // Remove the element as per index.
        System.out.println("After Remove Values of num is " + nums);

        nums.removeIf(n -> n > 3);

        System.out.println("The list using removeif condition" + nums);


        // HOw to list the even numbers from the list.

        ArrayList<Integer> EvenList = new ArrayList<Integer>(Arrays.asList(1, 3, 7, 6, 8, 10, 19, 34, 100));
        EvenList.removeIf(num -> num % 2 != 0);

        System.out.println("The Even numbers are " + EvenList);

        // How to list odd numbers in the list

        ArrayList<Integer> OddList = new ArrayList<Integer>(Arrays.asList(56, 878, 65, 90, 22, 45, 67));
        OddList.removeIf(num -> num % 2 == 0);

        System.out.println("The Odd NUmbers are " + OddList);


        // How to remove Duplicate numbers from the List

        ArrayList<Integer> dupNumList = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 5, 7, 8, 2, 3, 5, 9, 90, 56, 78, 1, 3, 6, 66));
        System.out.println("Duplicate List is " + dupNumList);
        LinkedHashSet<Integer> SetList = new LinkedHashSet<>(dupNumList);
        ArrayList<Integer> uniqueList = new ArrayList<Integer>(SetList);
        Collections.sort(uniqueList);
        System.out.println("The Unique list " + uniqueList);


        // How to compare two lists

        ArrayList<Integer> CompList1 = new ArrayList<Integer>(Arrays.asList(1, 3, 6, 745, 8, 98));
        ArrayList<Integer> CompList2 = new ArrayList<Integer>(Arrays.asList(1, 3, 6, 745, 8, 98, 34));

        //SOrt the lists
        Collections.sort(CompList1);
        Collections.sort(CompList2);

        CompList1.removeAll(CompList2);

        System.out.println("The Extra elemets are " + CompList1);


        // Display the List using Iterators
        // Display the list using explicit synchronization

        ArrayList<Integer> Series = new ArrayList<Integer>(Arrays.asList(4, 56, 34, 23, 78, 99, 100));

       synchronized (Series){

           Iterator<Integer> it = Series.iterator();

           while (it.hasNext()) {
               System.out.println("The numbers in the list is " + it.next());
           }

       }

       // Type 2 display the list using synchronization
        // No Explicit Synchronization is required  for add/remove/traverse
        CopyOnWriteArrayList<String> CWList = new CopyOnWriteArrayList<>(Arrays.asList("Mallik","Gayathri","ABhiram","Ananya"));

       Iterator it2 = CWList.iterator();
       while(it2.hasNext())
       {
           System.out.println("The numbers in the list is " + it2.next());
       }

    }
}
