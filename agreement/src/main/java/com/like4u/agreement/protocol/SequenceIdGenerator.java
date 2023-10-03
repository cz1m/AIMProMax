package com.like4u.agreement.protocol;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/9/20 20:24
 */
public class SequenceIdGenerator {
    private static AtomicInteger id =new AtomicInteger();
    public static int nextID(){
        return id.getAndIncrement();
    }
}
