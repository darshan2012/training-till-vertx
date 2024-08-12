package threadExamples.lockExamples;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterLock {
    private long count = 0;
    private ReentrantLock lock = new ReentrantLock();

    public void increment(){
        try {
            lock.lock();

            System.out.println(Thread.currentThread().getName() + " acquired the lock " + lock.getHoldCount());

            this.count++;
        }finally {
            lock.unlock();
        }
    }
    public long getCount(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " acquired the lock");
            System.out.println(lock.getQueueLength());
            System.out.println(lock.isFair());
            System.out.println(lock.isLocked());
            System.out.println(lock.isHeldByCurrentThread());
            return count;
        }finally {
            lock.unlock();
        }
    }
}
