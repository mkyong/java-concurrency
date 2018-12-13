package com.mkyong.concurrency.runnable;

public class RunnableExample2 implements Runnable {

    @Override
    public void run() {

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

    }

    public static void main(String[] args) {

        Thread t1 = new Thread(new RunnableExample2(), "magic1");
        t1.start();

        Thread t2 = new Thread(new RunnableExample2(), "magic2");
        t2.start();

        System.out.println("Done");

    }

}
