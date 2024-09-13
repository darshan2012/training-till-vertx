package com.example.starter.eventbus.pubsub;

import java.io.Serializable;
class B implements Serializable{
  int a;
}
public class A implements Serializable, Cloneable {
  int a;
  B b;
  public A(int a){
    this.a =a;
    b=new B();
  }

  @Override
  public String toString() {
    return "A{" +
      "a=" + a +
      "b=" + b.a +
      '}';
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    A obj = new A(this.a);
    B bobj = new B();
    bobj.a = b.a;
    obj.b = bobj;
    return obj;
  }
}
