package threadExamples.blockingQueueExample;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueuePractice {
    public void example(){
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(3);
        Producer producer = new Producer(bq);
        Consumer consumer = new Consumer(bq);
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        t1.start();
        t2.start();
        try {
            bq.put("1 " + System.currentTimeMillis());
            bq.put("2 " + System.currentTimeMillis());
            bq.put("3 " + System.currentTimeMillis());
            System.out.println("Size :" + bq.size());
            System.out.println("Rem capacity :" + bq.remainingCapacity());
            System.out.println("Contains : 2" + bq.contains("2"));
            System.out.println("Peek " + bq.peek());//first element if no element will return null
            System.out.println("Element " + bq.element());//if no element NoSuchElementException
            Collection dest = new ArrayList();
            bq.drainTo(dest);//drain all elelment from quque to dest
            System.out.println(dest);
            bq.drainTo(dest,2);//at max 2 elements will be drained
            System.out.println(dest);
            bq.put("4" + System.currentTimeMillis());//will block until space not available
            bq.add("5 " + System.currentTimeMillis());//will throw IllegalStateException if space not available
            System.out.println(bq.offer("6 " + System.currentTimeMillis()));//offer will return false if space is not available
            System.out.println(bq.offer("7 " + System.currentTimeMillis(),1000, TimeUnit.MILLISECONDS));//offer will wait for 1000milis
            String s = bq.take();//block until element become available
            System.out.println("took1 :" + s);
            s = bq.poll();//if no element in queue will return null
            System.out.println("took2 :" + s);
            s = bq.poll(10,TimeUnit.MILLISECONDS);
            System.out.println("took3 :"+s);
            Boolean b = bq.remove("2");//removeif present in the queue

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        catch (IllegalStateException e){
            System.out.println("Space not available");
        }
        catch (NoSuchElementException e){
            System.out.println("Queue is empty");
        }
    }
}
