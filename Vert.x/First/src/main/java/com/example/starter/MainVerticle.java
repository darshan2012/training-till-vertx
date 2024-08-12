package com.example.starter;

import io.vertx.core.*;
import io.vertx.core.net.NetServer;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
//    DeploymentOptions options = new DeploymentOptions().setThreadingModel(ThreadingModel.WORKER);
//    vertx.deployVerticle("com.mycompany.MyOrderProcessorVerticle", options);
    vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from Vert.x!");
    }).listen(8888).onComplete(http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
        } else {
        startPromise.fail(http.cause());
      }
    });
//    vertx.setPeriodic(1000,id ->{
//      System.out.println("its time");
//    });
//    Future<String> future = vertx.createDnsClient().lookup("google.com");
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
//    usingPromise();
//    one();
//    two();

    vertx.createNetServer().connectHandler(sock -> {

      // Create a pipe
      sock.pipeTo(sock);
      System.out.println();
    }).listen(1234);

//    server.connectHandler(socket -> {
//      socket.handler(buffer -> {
//        System.out.println("some bytes received : " + buffer.length());
//      });
//    });
  }

  public void one(){
    for(int i=0;i<11000;i++)
    {

        System.out.println("One " + i );

    }

  }
   public  void two(){
    for(int i=0;i<11000;i++)
    {
        System.out.println("two " + i);
    }
  }
  public void asyncOperation(Promise<String> promise) {
    vertx.setTimer(1000, id -> {
      // Simulate some asynchronous operation
      if (Math.random() > 0.5) {
        promise.complete("Success!");
      } else {
        promise.fail(new RuntimeException("Something went wrong"));
      }
    });
  }

  public void usingPromise() {
    Promise<String> promise = Promise.promise();

    asyncOperation(promise);

    promise.future().onSuccess(result -> {
      System.out.println("Operation succeeded with result: " + result);
    }).onFailure(cause -> {
      System.err.println("Operation failed with cause: " + cause.getMessage());
    });
  }
}
