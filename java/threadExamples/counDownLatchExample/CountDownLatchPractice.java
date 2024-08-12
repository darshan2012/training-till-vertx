package threadExamples.counDownLatchExample;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchPractice {
    public void example(){
        simpleUsage();
    }
    public void simpleUsage(){
        CountDownLatch latch = new CountDownLatch(5);

        Thread waiter = new Thread(() -> {
            System.out.println("Waiting");
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Wait Completed");
        },"Waiter");
        waiter.start();
        for(int i=0;i<5;i++)
        {
            new Thread(() -> {
                    latch.countDown();
                    System.out.println(latch.getCount());

                try {
                        Thread.sleep(100000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
            },"Worker "  + i).start();
        }

        System.out.println("Main thread executed");
    }
}
