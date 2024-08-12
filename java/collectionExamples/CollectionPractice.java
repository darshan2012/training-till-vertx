package collectionExamples;

import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class CollectionPractice {
    static private CollectionPractice obj = null;
    private CollectionPractice(){}
    static public CollectionPractice getCollectionObj(){
        if(obj == null)
            obj = new CollectionPractice();
        return obj;
    }

    public void arrayListExamples(){
        List<Integer> arr = Arrays.asList(1,2,3);

        List<Integer> arr1 = List.of(1,2,3,4);
//        arr1.set(2,5);

        List<Integer> nums = new ArrayList<>(Arrays.asList(32, 43));
        nums.add(39);
        nums.add(32);
        nums.add(12);
        nums.add(2);
        System.out.println(nums.containsAll(Arrays.asList(12,2,32)));
        Iterator itr = nums.iterator();
        System.out.println(nums.contains(12));
//      nums = List.of()
        while(itr.hasNext())
        {
            System.out.println(itr.next());
        }

//        System.out.println("2nd Index value "+ nums.get(2));
//        nums.set(2,42);
        nums.add(1,22);
        System.out.println(nums.size() + " " + nums);
        nums.remove(2);

        for(int x : nums)
        {
            System.out.println(x);
        }
        Collections.sort(nums);
        //Iterating using ListItroator
        System.out.println("Iterating using ListItroator");
        ListIterator<Integer> litr = nums.listIterator(nums.size());
        while(litr.hasPrevious())
            System.out.print(litr.previous() + " ");

        //forEach() Method
        System.out.println("\n\nforEach() Method ");
        nums.forEach(a-> System.out.print(a + " "));

        List<String> list = new ArrayList<String>(12    );
        System.out.println("\n\nsize " + list.size());
        System.out.println(list);
        list.add("aaa");
        list.add("bbb");
//        list.addLast("ddd");
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("file.txt"))){
            oos.writeObject(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("file.txt"))){
            ArrayList<String> l2 = (ArrayList<String>) ois.readObject();
            System.out.println("read from file" + l2);
            l2.remove("bbb");
            System.out.println(l2);
            list.retainAll(l2);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(list);
//         try(BufferedWriter writer = new BufferedWriter(new FileWriter("file.txt"))){
//            writer.write(String.valueOf(list));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        list.sort(() -> );
        list.clear();
        System.out.println(list.size());
        List<Student> studs = new ArrayList<>();
        studs.add(new Student(1,"one"));
        studs.add(new Student(2,"two"));
        studs.remove(1);
        CountDownLatch latch = new CountDownLatch(1);
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void hashSetExamples(){
        Set<Integer> s = new TreeSet<>();
        s.add(12);
        s.add(2);
        s.add(34);
        s.add(1);
//        s.add(null);
        s.add(2);
        for (Integer integer : s) System.out.println(integer);
        System.out.println(s);
        HashSet<String> set=new HashSet();
        set.add("One");
        set.add("Two");
        set.add("Three");
        set.add(null);
        set.add("Four");
        set.add("Five");
        Iterator<String> i=set.iterator();
        System.out.println(set);
        while(i.hasNext())
        {
            System.out.println(i.next());
        }
        Set<Student> studs = new TreeSet<>((a,b)->a.id-b.id);
        studs.add(new Student(3,"one"));
        studs.add(new Student(2,"two"));
        studs.add(new Student(1,"third"));
        studs.forEach(st-> System.out.println(st.id + " " + st.name));
    }
    public void useQueue(){
        Queue<Integer> queue = new LinkedList<>();
        System.out.println(queue.poll() + " " +  queue.peek());
        queue.add(22);
        queue.offer(1);
        queue.add(54);
        System.out.println(queue.poll());
        System.out.println(queue.peek());
        System.out.println(queue.remove());
        System.out.println(queue.size());
        Queue<Integer> pq = new PriorityQueue<>();
        pq.add(44);
        pq.add(22);
        pq.add(2);
        System.out.println(pq.peek());
        pq.remove();
        System.out.println(pq.peek());

        Queue<Integer> dq = new ArrayDeque<>();

    }
    public void useMap(){
        Map<Integer,String> map = new HashMap<>();
        map.put(3,"Three");
        map.put(1, "One");
        map.put(11,"Eleven");
        map.put(2,"two");
        map.put(22,"two two");
        map.forEach((k,v)-> System.out.println(k +  " " + v));
        System.out.println(map.get(2));
        System.out.println(map.remove(2));
        System.out.println(map.replace(11,"Eleven","one one"));
        System.out.println(map.get(11));
        System.out.println(map.containsValue("one one"));
        System.out.println(map.putIfAbsent(111,"Eleven"));
        System.out.println(map.put(11,"11"));
        System.out.println(map.getOrDefault(2,"2 Not Present"));
        map.put(11,"Eleven");
        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(System.out::println);

        NavigableMap<Integer,String> nmap = new TreeMap<>();
        Hashtable<Integer,String> hashtable = new Hashtable<>();

    }
    public void useStack(){
        Stack<Integer> s = new Stack<>();

    }

}
