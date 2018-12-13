package com.mkyong.concurrency.examples.sequence.generator;

public class UnSafeSequenceGenerator implements SequenceGenerator {

    private long value = 1;

    @Override
    public long getNext() {
        return value++;
    }

}
