package com.mkyong.concurrency.queue.simple;

import com.mkyong.concurrency.queue.simple.poison.ConsumerPoison;
import com.mkyong.concurrency.queue.simple.poison.ProducerPoison;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) {

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

        //new Thread(new Producer(queue)).start();
        //new Thread(new Consumer(queue)).start();

        Integer poison = -1;
        new Thread(new ProducerPoison(queue, poison)).start();
        new Thread(new ProducerPoison(queue, poison)).start();

        new Thread(new ConsumerPoison(queue, poison)).start();
        new Thread(new ConsumerPoison(queue, poison)).start();

    }

}
