package com.mkyong.concurrency.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {

        //System.out.println(ForkJoinAdd.startForkJoinSum(1_000_000));

        ForkJoinFibonacci task = new ForkJoinFibonacci(50);
        new ForkJoinPool().invoke(task);

        System.out.println(task.getNumber());

    }

}
