class Demo{
    int no;
    static Demo d;
    private Demo(){

    }
    Demo(int n){
        no = n;
    }
    static public Demo getDemo(){
        if(d == null)
            d = new Demo();
        return d;
    }
}