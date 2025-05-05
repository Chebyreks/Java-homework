package edu.phystech.hw4.stepper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kzlv4natoly
 */

public class Stepper {

    public enum Side {
        LEFT, RIGHT
    }

    private final List<Side> history = new ArrayList<>();
    private final Lock lock = new ReentrantLock();
    private Condition conditionIsLeftTurn = lock.newCondition();
    private Condition conditionIsRightTurn = lock.newCondition();
    private boolean isLeftTurn = true;

    public void leftStep() throws InterruptedException {
        lock.lock();
        try {
            while (!isLeftTurn) {
                conditionIsLeftTurn.await();
            }
            history.add(Side.LEFT);
            isLeftTurn = false;
            conditionIsRightTurn.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void rightStep() throws InterruptedException {
        lock.lock();
        try {
            while (isLeftTurn) {
                conditionIsRightTurn.await();
            }
            history.add(Side.RIGHT);
            isLeftTurn = true;
            conditionIsLeftTurn.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public List<Side> getHistory() {
        return history;
    }
}
