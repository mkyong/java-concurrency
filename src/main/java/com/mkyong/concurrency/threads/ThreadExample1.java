package com.mkyong.concurrency.threads;

// Java 8
public class ThreadExample1 {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            String name = Thread.currentThread().getName();
            System.out.println("Thread : [" + name + "] is started!");
            for (int i = 1; i <= 5; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println(name + ":" + i);
                } catch (InterruptedException e) {
                    System.out.println("Thread is interrupted!");
                }
            }
        });
        t1.setName("magic1");

        Thread t2 = new Thread(() -> {
            String name = Thread.currentThread().getName();
            System.out.println("Thread : [" + name + "] is started!");
            for (int i = 1; i <= 5; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println(name + ":" + i);
                } catch (InterruptedException e) {
                    System.out.println("Thread is interrupted!");
                }
            }
        });
        t2.setName("magic2");

        t1.start();
        t2.start();

        //if this thread is alive
        System.out.println("Is t1 alive? " + t1.isAlive());
        System.out.println("Is t2 alive? " + t2.isAlive());

        t1.join(); //Waits for this thread to die.
        t2.join(); //Waits for this thread to die.

        System.out.println("Is t1 alive? " + t1.isAlive());
        System.out.println("Is t2 alive? " + t2.isAlive());

        System.out.println("Done");

    }
}
