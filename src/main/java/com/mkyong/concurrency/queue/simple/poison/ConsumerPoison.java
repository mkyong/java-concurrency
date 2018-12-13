package com.mkyong.concurrency.queue.simple.poison;

import java.util.concurrent.BlockingQueue;

public class ConsumerPoison implements Runnable {

    private final BlockingQueue<Integer> queue;
    private final Integer POISON;

    @Override
    public void run() {

        try {
            while (true) {
                Integer take = queue.take();
                process(take);

                // if this is a poison pill, break, exit
                if (take == POISON) {
                    break;
                }

            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    private void process(Integer take) throws InterruptedException {
        System.out.println("[Consumer] Take : " + take);
        Thread.sleep(500);
    }

    public ConsumerPoison(BlockingQueue<Integer> queue, Integer POISON) {
        this.queue = queue;
        this.POISON = POISON;
    }
}
