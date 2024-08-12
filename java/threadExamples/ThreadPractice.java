package threadExamples;

import threadExamples.ThreadSignalExample.SignalCarrier;
import threadExamples.ThreadSignalExample.ThreadSignalPractice;
import threadExamples.atomicClassExamples.AtomicClassPractice;
import threadExamples.blockingQueueExample.BlockingQueuePractice;
import threadExamples.counDownLatchExample.CountDownLatchPractice;
import threadExamples.lockExamples.LockPractice;
import threadExamples.threadPoolExample.ThreadPoolExample;

import java.util.Map;

class A extends Thread{
    public A(){};
    public A(String threadName){
        super(threadName);
    }
    @Override
    public void run(){
        for(int i = 0;i<100;i++)
        {
            System.out.println("hii from " + Thread.currentThread());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}
class B extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("hello from " + Thread.currentThread());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

class C implements Runnable{
    @Override
    public void run() {
        for(int i = 0;i<100;i++)
        {
            System.out.println("hii");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}
class D implements Runnable{
    @Override
    public void run() {
        for(int i = 0;i<100;i++)
        {
            System.out.println("hello");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

class Counter{
    int cnt=0;
    synchronized public void increment(){
        cnt++;
    }
}
public class ThreadPractice {
    private void usingThreadClass() {
        A a = new A("Thread1");
        B b = new B();
        a.setPriority(Thread.MAX_PRIORITY);
        b.setPriority(Thread.MIN_PRIORITY);
        A a2 = new A();
        a2.start();
        a.start();
        b.start();
        System.out.println("Execution forwarded");
//        b.start(); //IllegalThreadStateException
        //Waiting for Thread to finish Execution.
        try {
            a.join();
            a2.join();
            b.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Main Thread exited.");
    }
    private void usingRunnableInterface(){
        C c = new C();
        D d = new D();

        Thread t1 = new Thread(c);
        Thread t2 = new Thread(d);

        t1.start();
        t2.start();

    }
    private void threadSynchronization() {
        Counter c = new Counter();
        Runnable cntObj1 = ()->{
            for (int i = 0; i < 100000; i++) {
                c.increment();
            }
        };
        Runnable cntObj2 = ()->{
            for (int i = 0; i < 100000; i++) {
                c.increment();
            }
        };
        Runnable cntObj3 = ()->{
            for (int i = 0; i < 100000; i++) {
                c.increment();
            }
        };
        Runnable cntObj4 = ()->{
            for (int i = 0; i < 100000; i++) {
                c.increment();
            }
        };
        Thread t1 = new Thread(cntObj1);
        Thread t2 = new Thread(cntObj2);
        Thread t3 = new Thread(cntObj3);
        Thread t4 = new Thread(cntObj4);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try
        {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        }catch (InterruptedException e)
        {
            System.out.println(e);
        }
        System.out.println(c.cnt);
    }
    private void usingLambdaExpression(){
        int cnt = 0;

        Runnable c = ()-> {
                for(int i = 0;i<100;i++)
                {
                    System.out.println("hii");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
            };
        Runnable d = () ->{
            for(int i = 0;i<100;i++)
            {
                System.out.println("hello");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        };
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(d);

        t1.start();
        t2.start();
    }
    private void useThreadLocalClass(){
        ThreadLocal<String> local = new ThreadLocal<>();
        InheritableThreadLocal<String> hlocal = new InheritableThreadLocal<>();
        Thread t1 = new Thread(() -> {
            local.set("T1");
            hlocal.set("InheritedT1");
            System.out.println("Inside Thread " + Thread.currentThread());
            System.out.println(local.get());
//            local.remove();
//            System.out.println(local.get());
            Thread t1c = new Thread(() -> {
                System.out.println(hlocal.get());
                System.out.println(local.get());
            });
            t1c.start();
        });
        t1.start();

    }

    public void practice(){
//        usingThreadClass();
//        usingRunnableInterface();
//        usingLambdaExpression();
//        threadSynchronization();
//        useThreadLocalClass();
//        CheckThenActExample();
//        new RaceConditionExample().example();
//        new ThreadSignalPractice().threadSignalExample();
//        new LockPractice().example();
//        new BlockingQueuePractice().example();
//        new ThreadPoolExample().example();
//        new AtomicClassPractice().example();
        new CountDownLatchPractice().example();
    }


}
