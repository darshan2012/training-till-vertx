package threadExamples.ThreadSignalExample;

public class ThreadSignalPractice {
    public void threadSignalExample(){
        SignalHolder signalCarrier = new SignalHolder();
//        SignalCarrier signalCarrier = new SignalCarrier();
        Thread waiter = new Thread(signalCarrier::doWait,"Waiter-1");
        Thread notifier = new Thread(signalCarrier::doNotify);

        waiter.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        signalCarrier.doNotify();
//        notifier.start();

        /* Problem here is if the notifier thread starts first then
         waiter would wait infinitely for the notify signal */
    }
}
