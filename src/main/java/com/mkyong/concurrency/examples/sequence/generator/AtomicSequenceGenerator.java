package com.mkyong.concurrency.examples.sequence.generator;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicSequenceGenerator implements SequenceGenerator {

    private AtomicLong value = new AtomicLong(1);

    @Override
    public long getNext() {
        return value.getAndIncrement();
    }
}
