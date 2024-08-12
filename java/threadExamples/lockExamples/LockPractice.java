package threadExamples.lockExamples;

public class LockPractice {

    public void example(){
        CounterLock cntObj = new CounterLock();
        Thread t1 = new Thread(() -> {
            for(int i=0;i<10000;i++)
                cntObj.increment();
        });
        Thread t2 = new Thread(() -> {
            for(int i=0;i<10000;i++)
                cntObj.increment();
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(cntObj.getCount());
    }


}
