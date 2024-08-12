package threadExamples.blockingQueueExample;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    BlockingQueue<String> bq;
    public Consumer(BlockingQueue<String> queue){
        bq = queue;
    }
    @Override
    public void run() {
        while(true){
            try {
                System.out.println("Consumed :" + bq.take());
            } catch (InterruptedException e) {

                throw new RuntimeException(e);
            }
        }
    }
}
