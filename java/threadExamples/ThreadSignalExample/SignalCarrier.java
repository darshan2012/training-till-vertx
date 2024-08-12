package threadExamples.ThreadSignalExample;

/* Problem here is if the notifier thread starts first then
 waiter would wait infinitely for the notify signal */
public class SignalCarrier {
    public void doWait(){
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + " calling wait()");
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " exited wait()");
        }
    }
    public synchronized void doNotify(){
        System.out.println(Thread.currentThread().getName() + " calling notify()");
        notify();
        System.out.println(Thread.currentThread().getName() + " exited notify()");
    }
    public synchronized void doNotifyAll(){
        System.out.println(Thread.currentThread().getName() + " calling notifyAll()");
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " exited notifyAll()");
    }
}
