package com.mmandava.samples.onlydefaultinterface;

public interface first {

    void listen();
    default void show() {
        System.out.println("This is java8 feature");
    }

    static void connect() {
        System.out.println("Connecting to the socket");
    }

    default void show1() {
        System.out.println("This is java8 feature duplicate call");
    }

}
