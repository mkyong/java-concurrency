package com.mkyong.concurrency.queue.simple.poison;

import java.util.concurrent.BlockingQueue;

public class ProducerPoison implements Runnable {

    private final BlockingQueue<Integer> queue;
    private final Integer POISON;

    @Override
    public void run() {

        try {
            process();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            while (true) {
                try {
                    queue.put(POISON);
                    break;
                } catch (InterruptedException e) {
                    //...
                }
            }
        }

    }

    private void process() throws InterruptedException {

        // Put 20 elements into Queue
        for (int i = 0; i < 20; i++) {
            System.out.println("[Producer] Put : " + i);
            queue.put(i);
            System.out.println("[Producer] Queue remainingCapacity : " + queue.remainingCapacity());
            Thread.sleep(100);
        }

    }

    public ProducerPoison(BlockingQueue<Integer> queue, Integer POISON) {
        this.queue = queue;
        this.POISON = POISON;
    }

}
