package com.mmandava.samples.DemoLamda;



public class testlambda {

    public static void main(String[] args) {
        lamdainterf  obj1;
        obj1 = (int arg1) ->
            {
                System.out.println("Hello " + arg1);
                return arg1*2;
            };

        System.out.println("The return value is " + obj1.display(100));

    }



}
