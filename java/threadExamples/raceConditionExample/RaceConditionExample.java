package threadExamples.raceConditionExample;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class CheckThenActExample {

    public void checkThenAct(Map<String, String> sharedMap,Integer i) {
        if(sharedMap.containsKey("key")){
            String val = sharedMap.remove("key");
            if(val == null) {
                System.out.println(i + " Value for 'key' was null");
            }
        } else {
            sharedMap.put("key", "value");
        }
    }
}
class RaceConditionExample {
    public void example(){
        Map<String,String> sharedMap = new ConcurrentHashMap<>();
        CheckThenActExample obj = new CheckThenActExample();
        sharedMap.put("one","one");
        Thread t1 = new Thread(() -> {
            for(int i=0;i<100000;i++) {
                obj.checkThenAct(sharedMap,i);
            }
        });
        Thread t2 = new Thread(() -> {
            for(int i=0;i<100000;i++)
                obj.checkThenAct(sharedMap,i);
        });
        t1.start();
        t2.start();
    }
}
