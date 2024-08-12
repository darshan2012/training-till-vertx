package threadExamples.threadPoolExample;

import java.util.*;
import java.util.concurrent.*;

public class ThreadPoolExample {
    public static Runnable newRunnable(String message){
        return new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " " + message);
            }
        };
    }
    public static Callable newCallable(String message){
        return new Callable() {

            @Override
            public Object call() throws Exception {
                String msg = Thread.currentThread().getName() + " : " + message;
                return msg;
            }
        };
    }
    public void example() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(newRunnable("Task1"));
        executorService.execute(newRunnable("Task2"));
        executorService.execute(newRunnable("Task3"));
        Future future = executorService.submit(newRunnable("Task4"));

        System.out.println("Runnable: " + future.isDone());
        try {
            /* will give result but run method do not return anything so it will be null
            * However the thread which call this get method (Main thread In this case)
            *  will block until the result is available
            * */
            System.out.println("Runnable: " + future.get());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Runnable: " + future.isDone());
        future = executorService.submit(newCallable("Task5"));
        System.out.println("callable :" + future.isDone());
        try {
            System.out.println("Callable : " + future.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Callable : " + future.isDone());


        //invokeAny() Method
        List<Callable<String>> callables = new ArrayList<>();
        callables.add(newCallable("Task6.1"));
        callables.add(newCallable("Task6.2"));
        callables.add((newCallable("Task6.3")));

        try {
            String result = (String) executorService.invokeAny((Collection) callables);
            System.out.println(result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        //invokeAll() Method
        try {
            List<Future<String>> results = executorService.invokeAll((Collection<Callable<String>>) callables);
            for(Future f : results)
                System.out.println(f.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
    
    }
}
