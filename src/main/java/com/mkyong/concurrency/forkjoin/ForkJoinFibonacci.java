package com.mkyong.concurrency.forkjoin;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

//https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/RecursiveTask.html
//https://www.javaspecialists.eu/archive/Issue201.html
public class ForkJoinFibonacci extends RecursiveAction {

    private static final long threshold = 10;
    private volatile long number;

    public ForkJoinFibonacci(long number) {
        this.number = number;
    }

    public long getNumber() {
        return number;
    }

    @Override
    protected void compute() {

        long n = number;
        if (n <= threshold) {
            number = fib(n);
        } else {
            ForkJoinFibonacci f1 = new ForkJoinFibonacci(n - 1);
            ForkJoinFibonacci f2 = new ForkJoinFibonacci(n - 2);
            ForkJoinTask.invokeAll(f1, f2);
            number = f1.number + f2.number;
        }
    }

    private static long fib(long n) {
        if (n <= 1) return n;
        else return fib(n - 1) + fib(n - 2);
    }

}
