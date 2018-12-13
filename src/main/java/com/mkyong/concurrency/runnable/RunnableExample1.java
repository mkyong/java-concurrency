package com.mkyong.concurrency.runnable;

public class RunnableExample1 {

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            System.out.println("Thread : [" + name + "] is started!");

            for (int i = 1; i <= 5; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println(name + " : " + i);
                } catch (InterruptedException e) {
                    System.out.println("Thread is interrupted!");
                }
            }
        };

        Thread t1 = new Thread(runnable, "magic1");
        t1.start();

        Thread t2 = new Thread(runnable, "magic2");
        t2.start();

        t1.join();
        t2.join();

        // print this after t1 and t2 are done.
        System.out.println("Done");

    }

}
