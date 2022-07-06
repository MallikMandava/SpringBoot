package com.mmandava.samples.compvscompl;

import java.util.Comparator;

public class newcomp implements Comparator <Laptop> {



    @Override
    public int compare(Laptop lap1, Laptop lap2) {
        if (lap1.getPrice() > lap2.getPrice() ) {
            return 1;
        }
        else
        {
            return -1;
        }

    }
}
