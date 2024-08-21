package com.example.starter.buffer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;

public class BufferUsage extends AbstractVerticle {
  @Override
  public void start() throws Exception {
//    Buffer buff = Buffer.buffer();
    creatingBuffer();
  }
  public void creatingBuffer(){
    //creating empty buffer
    Buffer buff = Buffer.buffer();
    //creating buffer form string
    Buffer b1 = Buffer.buffer("Hello World");
    //Buffer with initial size to avoid resizing if you know the size or know the buffer will be large
    Buffer b2 = Buffer.buffer(10000);

    //appending to buffer
    buff.appendInt(1).appendString("this");

    buff.setInt(100,2);
    System.out.println(buff.getInt(0));
    System.out.println(buff.getString(4,8));
    //reading from buffer
    for (int i=0;i<buff.length();i+=4)
    {
      System.out.println(buff.getInt(i));
    }
  }
}
