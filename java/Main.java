import java.io.BufferedReader;
import java.io.IOException;
//import java.util.concurrent.;
import algorithms.Algorithms;
import collectionExamples.CollectionPractice;
import datetimeAPIExamples.DateTimePractice;
import fileioExamples.FileIOPractice;
import genericsExamples.GenericsPractice;
import recordExamples.RecordClassPractice;
import streamAPIExamples.StreamAPIPractice;
import threadExamples.*;
import stringExamples.*;
import threadExamples.lockExamples.LockPractice;

import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.*;

class TestException{
    public void a(){
        System.out.println("in A");
        try {
            b();
        } catch (Exception e) {
            System.out.println("Exception handled.\n" + e.getMessage());
        }
    }
    public void b(){
        System.out.println("in B");
        c();
    }
    public void c() {
        System.out.println("in C");
        throw new ArithmeticException();
    }
}
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@FunctionalInterface
interface A{
    static public void a(){
        System.out.println("klakdj");
    }
    void show();
}
class B implements A{
    final private int a=19;
    public void show(){
        System.out.println("show in B");
    }
}
class C extends B {
    private int a;
}
class Ooo{
    /**
     *
     */
    @Override
    public void finalize(){System.out.println("object is garbage collected");}

}

public class Main {


    enum Status {
        RUNNING, PAUSED, COMPLETED
    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        C c  = new C();
        
//        System.out.println(c.a);
//        String str = "1111111111111111111111111111111111111111111111";
//        String s2 = new String("1111111111111111111111111111111111111111111111");
//        String a1 = new String("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        String a2 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//        Thread.sleep(1000000000);
//        Runtime runtime = Runtime.getRuntime();
//        System.out.println(runtime.availableProcessors());
//        System.out.println(runtime.freeMemory()/1024);
//        System.out.println(runtime.totalMemory());
//        InetAddress address = InetAddress.getByName("google.com");
//        System.out.println(address);
//        Ooo s1=new Ooo();
//        Ooo s2=new Ooo();
//        s1=null;
//        s2=null;
//        System.gc();
//        System.out.println(Double.NaN * Double.POSITIVE_INFINITY);

//        List<Character> s = new ArrayList<Character>();
//        s.add('a');
//        s.add('b');
//        System.out.println(s);

//        ThreadPractice t = new ThreadPractice();
//              t.practice();
//        new Algorithms().examples();
//        new LockPractice().example();

//        CollectionPractice obj = CollectionPractice.getCollectionObj();

//        obj.arrayListExamples();
//        obj.hashSetExamples();
//        obj.hashSetExamples();
//        obj.useQueue();
//        obj.useMap();
//        GenericsPractice gp= new GenericsPractice();
//        gp.example();
//        FileIOPractice fio = new FileIOPractice();
//        fio.fileClassExample();
        //        fio.fileIOStreamExample();
//        fio.bufferedIOStreamExamples();
//        fio.bufferedReaderWritedExample();
        //        RecordClassPractice recordPrac = new RecordClassPractice();
//        recordPrac.Examples();

//        A a = ()-> System.out.println("hello from inner class");
        StreamAPIPractice stream = new StreamAPIPractice();
        stream.mapExample();
        //        a.show();
//        stream.examples();

//        DateTimePractice dateTime = new DateTimePractice();
//        dateTime.useLocalDateTime();
//        dateTime.useLocalTime();
//        dateTime.useLocalDate();
//        dateTime.useZonedDateTime();
//        dateTime.useInstantDurationPeriod();
//        dateTime.useDateTimeFormatter();

//
        //        Practice p = new Practice();
//        p.comparision();
//        p.stringConcatenation();
//        p.subStringExamples();
//        System.out.println(p.toString());
//        p.stringTokenizedExample();
//        String s1 = "hey";

//        String s2 = "hey";
//        String s3 = "hey";
//        System.out.println(s1.equals(s3) + " and " + s1 == s3);
//        double x = -34.0/0.0;
//        System.out.println(x + " " + Double.POSITIVE_INFINITY);
//
//        TestException te = new TestException();
//        te.a();
//        Scanner scn = new Scanner(System.in);
//
//        System.out.println("Enter an integer & a String");
//
//        // Using nextInt() to parse integer values
//        int a = scn.nextInt();
//
//        // Using nextLine() to parse string values
//        String b = scn.next();
//
//        // Display name and age entered above
//        System.out.printf("You have entered:- " + a + " "
//                + "and name as " + b);
//
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        int aa = Integer.parseInt(br.readLine());
//
//        String ss = br.readLine();
//        System.out.println(aa + " " + ss);

//        System.out.println("character : "+ input);
//        String st = "aaa";
//
//        System.out.println(st.length());
//        char arr[] = {1,2,3};
//        System.out.println(arr.length);
//        A a = new A(){
//            public void show(){
//                System.out.println("show method inner");
//            }
//        };
//        A.a();
//        B b = new B();
//        b.show();

//        Status s = Status.PAUSED;
//        Demo d = Demo.getDemo();
//        Demo d1 = Demo.getDemo();
//        System.out.println(d.no);
//        System.out.println(Status.valueOf("PAUSED"));
//        System.out.println(s.hashCode());
//        switch (s) {
//            case RUNNING -> System.out.println("RUNNING");
//            case PAUSED -> System.out.println("PAUSED");
//            case COMPLETED -> System.out.println("COMPLETED");
//        }
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
//        List<Integer> al = new ArrayList<Integer>();
//        al.add(1);
//        al.add(1,2);
//        al.add(1,3);
//        System.out.println(al);
//
//        // to see how IntelliJ IDEA suggests fixing it.
//        System.out.printf("Hello and welcome!");
//
//        for (int i = 1; i <= 5; i++) {
//            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
//            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
//            System.out.println("i = " + i);
//        }
    }
}