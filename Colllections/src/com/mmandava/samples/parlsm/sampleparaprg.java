package com.mmandava.samples.parlsm;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class sampleparaprg {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        int coreCount = Runtime.getRuntime().availableProcessors();

        ExecutorService executorService = Executors.newFixedThreadPool(coreCount);

        for (int i = 0; i < 10; i++) {
            executorService.execute(new task());
        }


        executorService.shutdown();

    }

    static class task implements Runnable {

        private int i=0;
        public void run() {
            System.out.println("running the task");
            pluck();
            System.out.println("The value of i is" + i);
            System.out.println( "The Thread is " + Thread.currentThread().getId() + "with Time as " +  Instant.now());

        }
        public void pluck()
        {
            ++i;
        }

    }
}




