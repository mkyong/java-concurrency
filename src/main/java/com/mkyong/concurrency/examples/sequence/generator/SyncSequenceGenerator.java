package com.mkyong.concurrency.examples.sequence.generator;

public class SyncSequenceGenerator implements SequenceGenerator {

    private long value = 1;

    @Override
    public synchronized long getNext() {
        return value++;
    }
}
