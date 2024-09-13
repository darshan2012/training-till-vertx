package com.example.starter.future;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;

public class FutureUsage extends AbstractVerticle {
  public void start(){
    practiceFuture();
//     Future<String> future = vertx.createDnsClient().lookup("google.com");
//    future.onComplete(ar -> {
//      if(ar.succeeded()){
//        System.out.println(ar.result());
//      }else
//        System.out.println("could not reach vertx.io");
//    });
//
//    Future<String> future2 = vertx.createDnsClient().lookup("google.com");
//    future2.toCompletionStage().whenComplete((ip,err) -> {
//      if(err == null){
//        System.out.println(ip);
//      }else
//        err.printStackTrace();
//    });
  }
  public void practiceFuture(){

    System.out.println("In practice future : " + Thread.currentThread());
      Future<String> future = usingPromise();
      future.onComplete(res -> {
              if(res.succeeded())
                  System.out.println("Success : " + res);
              else
                  System.out.println("Failed : " + res.cause());
          }
      );
//    vertx.setTimer(1000,id -> {
//      Future<String> future = usingPromise();
//      future.onComplete(res -> {
//          if(res.succeeded())
//            System.out.println("Success : " + res);
//          else
//            System.out.println("Failed : " + res.cause());
//        }
//      );
//    });

  }
  public void asyncOperation(Promise<String> promise) {
    vertx.setPeriodic(1000, id -> {
      // Simulate some asynchronous operation
      if (Math.random() > 0.5) {
        promise.complete("Success!");
      } else {
        promise.fail(new RuntimeException("Something went wrong"));
      }
      //can not change after completion(complete or fail)
//      promise.complete("Something wrong fixed");
//        System.out.println("here");
//      promise.fail("failed");
    });
  }

  public Future<String> usingPromise() {
    Promise<String> promise = Promise.promise();
    System.out.println("in using promise "  + Thread.currentThread());

    asyncOperation(promise);

    return promise.future();
  }
}
