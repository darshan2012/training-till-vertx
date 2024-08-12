package streamAPIExamples;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPIPractice {
    public void examples(){
//        Predicate //: return as boolean test() result
//        Consumer //: input ,no result
//        Function  //: Takes input and returns output
//        Supplier //: no input only output
        List<Integer> nums = Arrays.asList(64,23,12,21,53,15);
        Stream<Integer> s1 = nums.stream();

//        nums.sort((a,b)-> a-b);
//        Predicate
        Stream<Integer> s2 = s1.filter(n -> n % 2 == 1);
        System.out.println(s2);

        String[] names = {"one","two","three","four"};
        Stream<String> s3 = Arrays.stream(names);

        Stream<Integer> s4 = Stream.of(1, 2, 3, 4, 5);
        s4.forEach(System.out::println);
        List<Integer> collect = Stream.iterate(0, n -> n + 1).limit(10).collect(Collectors.toList());
        System.out.println(collect);

//        List<Integer> nos = Stream.generate(() -> (Math.random() * 100))
//                .limit(10).collect(Collectors.toList()));
//        System.out.println(reduce);


        Integer reduce = nums.stream()
                .filter(n->n%2==0)
                .map(n -> n + 4)
                .reduce(0, Integer::sum);

    }
    public void mapExample(){
        Map<Integer,List<Integer>> m = new HashMap<>();
        for(int i=0;i<10;i++)
        {
            List<Integer> list = new ArrayList<>(10);
            for (int j = 0; j < 10; j++) {
                list.add(j);
            }
            m.put(i,list);
        }
        var newMap = m.entrySet().stream().filter((e) ->{
            if(e.getKey() % 2 == 0)
            {
                e.setValue(e.getValue().stream().filter(v -> v%2==0).collect(Collectors.toList()));
                return true;
            }else
                return false;
        });
        newMap.forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));

        Map<Integer,Map<String,String>> m1 = new HashMap<>();
        Map<String,String> ms = new HashMap<>();
        ms.put("as","aaa");
        ms.put("AKSD","ASK");
        ms.put("aklfd","akj");
        m1.put(1,ms);
        ms.clear();
        ms.put("OIO","ASA");
        ms.put("OWE","SAK");
        m1.put(2,ms);
        ms.clear();
        ms.put("AKJS","ADK");
        ms.put("aSK","DKF");
        ms.put("ASDK","MDFO");
        m1.put(3,ms);
        ms.clear();
//        values.forEach(v -> System.out.println(v));
    }
}

