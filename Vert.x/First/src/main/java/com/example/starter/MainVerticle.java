package com.example.starter;

import com.example.starter.buffer.BufferUsage;
import com.example.starter.dnsClient.DNSClientUsage;
import com.example.starter.eventbus.EventBusUsage;
import com.example.starter.json.JsonObjectUsage;
import com.example.starter.socket.SocketUsage;
import io.vertx.core.*;
import io.vertx.core.net.NetServer;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {



//    vertx.deployVerticle(JsonObjectUsage.class.getName());
//    vertx.deployVerticle(new SecondVerticle()).onComplete(res ->{
//      if(res.succeeded())
//      System.out.println("Deployed : " + res.result());
//      else
//        System.out.println("Unsuccessful deployment : " + res.cause());
//    });
//    vertx.deployVerticle("com.example.starter.SecondVerticle",new DeploymentOptions().setInstances(7));
//    vertx.deployVerticle(SecondVerticle.class.getName(),new DeploymentOptions().setInstances(5).setThreadingModel(ThreadingModel.WORKER));

//    vertx.deployVerticle(new BufferUsage());

//    vertx.deployVerticle(new EventBusUsage());
//    vertx.createHttpServer().requestHandler(req -> {
//      req.response()
//        .putHeader("content-type", "text/plain")
//        .end("Hello from Vert.x!");
//    }).listen(8888).onComplete(http -> {
//      if (http.succeeded()) {
//        startPromise.complete();
//        System.out.println("HTTP server started on port 8888");
//        } else {
//        startPromise.fail(http.cause());
//      }
//    });
//    System.out.println("its Async " + Thread.currentThread());
//    Context context1 = vertx.getOrCreateContext();
//    if (context.isEventLoopContext()) {
//      System.out.println("Context attached to Event Loop");
//    } else if (context.isWorkerContext()) {
//      System.out.println("Context attached to Worker Thread");
//    } else if (! Context.isOnVertxThread()) {
//      System.out.println("Context not attached to a thread managed by vert.x");
//    }
//    context1.runOnContext(v -> {
//      System.out.println("Executed async in the same context" + v);
//    });

//    vertx.setPeriodic(1000,id ->{
//      System.out.println("its time");
//    });

//    usingPromise();
//    one();
//    two();
//    vertx.deployVerticle(new SocketUsage());
    vertx.deployVerticle(new DNSClientUsage());
//    vertx.createNetServer().connectHandler(sock -> {
//
//      // Create a pipe
//      sock.pipeTo(sock);
//      System.out.println();
//    }).listen(1234);

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

}
