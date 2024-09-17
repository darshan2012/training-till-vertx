package com.example.starter;

import com.example.starter.eventbus.EventBusUsage;
import com.example.starter.fileSystem.FileSystemUsage;
import com.example.starter.future.FutureUsage;
import com.example.starter.socket.FileServer;
import com.example.starter.verticle.ConfigVerticle;
import com.example.starter.verticle.StandardVerticle;
import com.example.starter.web.router.RouterVerticle;
import com.example.starter.zmq.ZMQServerVerticle;
import io.vertx.core.*;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    startPromise.complete();
    vertx.deployVerticle(new EventBusUsage());
//    vertx.deployVerticle(new FutureUsage());
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
//    vertx.deployVerticle(new DNSClientUsage());
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
