package threadExamples.blockingQueueExample;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    BlockingQueue<String> bq;

    public Producer(BlockingQueue<String> queue){
        this.bq = queue;
    }
    @Override
    public void run() {
        while(true){
            long timeMillis = System.currentTimeMillis();
            try {
                this.bq.put("" + timeMillis);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
