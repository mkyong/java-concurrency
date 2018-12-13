package com.mkyong.concurrency.threads;

//classic
public class ThreadExample2 extends Thread {

    @Override
    public void run() {

        String name = Thread.currentThread().getName();
        System.out.println("Thread : [" + name + "] is started!");

        for (int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(i);
            } catch (InterruptedException e) {
                System.out.println("Thread is interrupted!");
            }
        }

    }

    public static void main(String[] args) {

        ThreadExample2 a = new ThreadExample2();
        a.setName("Magic");
        a.start();

        System.out.println("Done.");

    }

}
