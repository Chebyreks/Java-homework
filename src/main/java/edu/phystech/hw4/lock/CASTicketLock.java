package edu.phystech.hw4.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author kzlv4natoly
 */
public class CASTicketLock {
    private final AtomicInteger nextTicket = new AtomicInteger();
    private final AtomicInteger currentTicket = new AtomicInteger();

    public void lock() {
        int ticket = nextTicket.getAndIncrement();
        while (currentTicket.get() != ticket) { } // Чилл
    }

    public void unlock() {
        currentTicket.incrementAndGet();
    }
}
