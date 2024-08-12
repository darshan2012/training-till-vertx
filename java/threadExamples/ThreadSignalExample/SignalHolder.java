package threadExamples.ThreadSignalExample;

/* To solve the problem with signalCarrier class this class was created */
public class SignalHolder {
    private boolean signalRaised = false;
    private boolean isThreadWaiting = false;
    public void doWait(){
        synchronized (this){
            if(signalRaised){
                System.out.println(Thread.currentThread().getName() + " Signal was already raised");
                this.signalRaised = false;
                return;
            }
            System.out.println(Thread.currentThread().getName() + " calling wait()");
            try {
                this.isThreadWaiting = true;
                this.wait();
                this.isThreadWaiting = false;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " exited wait()");
        }
    }
    public synchronized void doNotify(){

        System.out.println(Thread.currentThread().getName() + " calling notify()");
        if(!isThreadWaiting)
            signalRaised = true;
        notify();
        System.out.println(Thread.currentThread().getName() + " exited notify()");
    }
    public synchronized void doNotifyAll(){
        System.out.println(Thread.currentThread().getName() + " calling notifyAll()");
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " exited notifyAll()");
    }
}
