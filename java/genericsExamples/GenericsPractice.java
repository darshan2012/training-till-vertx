package genericsExamples;
class A<T>{
    public T value;
    public A(){}
    public A(T value)
    {
        this.value = value;
    }
}
public class GenericsPractice {
    public void example(){
        A<String> stringVal = new A<>("HELlo");
        System.out.println(stringVal.value);
        System.out.println(new A<Integer>(9).value);

    }
}
