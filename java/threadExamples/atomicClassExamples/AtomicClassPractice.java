package threadExamples.atomicClassExamples;

import java.util.concurrent.atomic.AtomicInteger;
class Value{
    Integer a;
    public Value(int a){
        this.a = a;
    }
    public void increment(){
        a++;
    }
    public Integer get(){return a;}
}

public class AtomicClassPractice {
    public void example(){
        AtomicInteger ai = new AtomicInteger(0);
        Value val = new Value(0);
        Thread t1 = new Thread(() -> {
            int cnt=0;
            synchronized (ai) {

                for (int i = 0; i < 100000; i++) {
                    cnt++;
                    ai.incrementAndGet();
                    val.increment();
                }
                System.out.println(Thread.currentThread().getName() + " " + ai.get() + " " + val.get() + " " + cnt);
            }});
        Thread t2 = new Thread(()-> {
            int cnt=0;
            synchronized (val) {
                for (int i = 0; i < 100000; i++) {
                    cnt++;
                    ai.incrementAndGet();
                    val.increment();
                }
                System.out.println(Thread.currentThread().getName() + " " + ai.get() + " " + val.get() + " " + cnt);
            }});
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(ai.get() + " " + val.get());
        System.out.println(ai.compareAndExchangeAcquire(200000,10));
        System.out.println(ai.get());
    }

}
