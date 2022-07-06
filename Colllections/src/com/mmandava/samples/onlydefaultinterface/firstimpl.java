package com.mmandava.samples.onlydefaultinterface;

public class firstimpl implements first {

    @Override
    public void listen() {
        System.out.println("Listening on socket");
    }

    public void show()
    {
        System.out.println("This is java8 feature");
    }
}
