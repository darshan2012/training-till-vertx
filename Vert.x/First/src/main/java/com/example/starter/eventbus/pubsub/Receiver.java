package com.example.starter.eventbus.pubsub;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;

public class Receiver extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    EventBus eb = vertx.eventBus();
//    eb.consumer("data",message -> {
//      System.out.println(message.body() + Thread.currentThread().getName());
//    });
    MessageConsumer<String> consumer = eb.consumer("data");
    consumer.completionHandler(res -> {
      if(res.succeeded()){
        System.out.println("handler registered to Data");
      }
      else
        System.out.println("Registration failed!");
    });
    consumer.handler(data -> {
      System.out.println(data.body());
//      System.out.println("Headers  : " + data.headers() + " " + data.headers().get("header")  + " \n Address : " + data.address() + " \n Reply address : " + data.replyAddress());

    });
    vertx.setTimer(10000,id -> {
      consumer.unregister().onComplete(res ->  {
        if(res.succeeded())
          System.out.println("Handler unregistered " + res.result()  );
        else
          System.out.println("unregistering failed " + res.cause());
      });
    });

  }
}
