package recordExamples;
//class Student{
//    private int id;
//    private String name;
//
//    public Student(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//}
record Student(int id,String name){
      public Student{
        if(id <= 0)
            throw new IllegalArgumentException("ID cannot be less than or equal to zero!");
    }
}
public class RecordClassPractice {
    public void Examples(){
        Student s1 = new Student(0, "Darshan");
        Student s2 = new Student(2,"Vedant");

        System.out.println(s1);
        System.out.println(s1.equals(s2));
    }
}
