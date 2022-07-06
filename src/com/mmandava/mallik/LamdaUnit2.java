package com.mmandava.mallik;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class LamdaUnit2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<peoples> plist = Arrays.asList(
				
					new peoples("Mallik","Mandava",38),
					new peoples("Abhiram","Mandava",6),
					new peoples("Ananya","Mandava",2),
					new peoples("Gayathri","Mandava",30)
					
		);
		
		// Sort the List starts with FirstName
		
		Collections.sort(plist,(p1,p2) -> p1.getfName().compareTo(p2.getfName()));
		System.out.println("Sorted List");
		System.out.println("------------------------------");
		printconditionally(plist,p -> true);
		
		System.out.println("------------------------------");
		System.out.println("Starts With M");
		System.out.println("------------------------------");
		
		printconditionally(plist,p -> p.getlName().startsWith("M"));
		
		System.out.println("------------------------------");
		System.out.println("Starts With A");
		System.out.println("------------------------------");
		printconditionally(plist,p -> p.getfName().startsWith("Z"));

	}
	
	private static void printconditionally(List<peoples> plist , condition cond)
	{
		for (peoples p : plist )
		{
			if (cond.test(p))
			{
				System.out.println(p);
			}
		}
		
	}
	

}



interface condition{
	boolean test(peoples s);
}