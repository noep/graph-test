package io.noep.graph;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Desc  :
 */
public class SequentialIdGenerater implements IdGenerator {

    private AtomicInteger integer;

    public SequentialIdGenerater() {
        integer = new AtomicInteger(0);
    }

    @Override
    public int getId() {
        return integer.incrementAndGet();
    }
}
