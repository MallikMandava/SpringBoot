package com.mmandava.mallik;

public class LamdaUnit1 {

	public static void main(String[] args) {
		
		LamdaPrint LamdaFunction1 = () -> System.out.println("Hello World!");
		LamdaFunction1.print();
		
		LamdaAdd LamdaFunction2 =  (int a , float b ) ->    a + b;
		System.out.println(LamdaFunction2.add(1, 2.5f));
		
	}
	
}

interface LamdaPrint
{
		public void print();
}


interface LamdaAdd
{
		public float add(int arg1 , float d);
}
