package com.mkyong.concurrency.threads;

public class ThreadExample3 {

    public static void main(String[] args) throws InterruptedException {

        Calculator obj = new Calculator();

        // simulate a long computation process
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(5000);
                obj.num1 = 20;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // simulate a short computation process
        Thread t2 = new Thread(() -> obj.num2 = 1);

        t1.start();
        t2.start();

        //To sum both numbers, we need to wait t1 and t2, both return a number.
        t1.join(); // make sure t1 is done
        t2.join(); // make sure t2 is done

        System.out.println("Sum : " + obj.sum());

    }

    private static class Calculator {
        private int num1;
        private int num2;

        private int sum() {
            return num1 + num2;
        }
    }
}

