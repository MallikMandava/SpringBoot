package com.mmandava.samples.onlycomparator;

import java.util.Comparator;

public class enhancedsort implements Comparator<Integer> {

    public int  compare(Integer arg1 , Integer arg2)
    {
        if ( arg1%10 > arg2%10)
        {
            return 1;
        }
        else {
            return -1;
        }
    }

}
