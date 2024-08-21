package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Promise;

public class SecondVerticle extends AbstractVerticle {

  public void start(Promise<Void> startPromise){

    System.out.println("In the second Verticle ");
    someMethod().onComplete(r -> {
      System.out.println(r.result());
    });

    vertx.setTimer(1000,id -> {
      System.out.println("Some startup task");
      startPromise.complete();
//        startPromise.fail("failed");
    });

    Context context = vertx.getOrCreateContext();
    context.put("data","hello");
    if(context.isWorkerContext())
      vertx.setTimer(1999,id -> {
        System.out.println("In second verticle Worker " + Thread.currentThread() + " name : " + Thread.currentThread().getName());
      });
    else if (context.isEventLoopContext()) {
      vertx.setTimer(1999,id -> {
        System.out.println("In second verticle EventLoop " + Thread.currentThread() + " name : " + Thread.currentThread().getName());
      });
    }
  }
  public Future<String> someMethod(){
//    int cnt=0;
    Promise<String> promise = Promise.promise();
    vertx.setTimer(10000, id -> {
      System.out.println("Some Method");
      promise.complete("Done");
    });
    return promise.future();
//    for(int i=0;)
  }

  @Override
  public void stop() throws Exception {
    System.out.println("verticle in undeployed");
    super.stop();
  }
}
