package com.mkyong.concurrency.examples.sequence;

import com.mkyong.concurrency.examples.sequence.generator.SequenceGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class PrintSequenceCallable implements Callable<List<Long>> {

    private SequenceGenerator sequenceGenerator;

    public PrintSequenceCallable(SequenceGenerator sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public List<Long> call() throws Exception {

        List<Long> ids = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Thread.sleep(100); //take a nap
            ids.add(sequenceGenerator.getNext());
        }

        return ids;

    };

}
