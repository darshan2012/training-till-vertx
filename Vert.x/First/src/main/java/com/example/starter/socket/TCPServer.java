package com.example.starter.socket;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;

public class TCPServer extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    System.out.println("here");
    NetServer server = vertx.createNetServer(new NetServerOptions().setLogActivity(true));
    server.connectHandler(socket -> {

      System.out.println("Client connected: " + socket.remoteAddress());

      socket.handler(buffer -> {
        System.out.println("Received data: " + buffer.toString());
        socket.write(buffer);
      });

      socket.closeHandler(v -> {
        System.out.println("Client disconnected: " + socket.remoteAddress());
      });
    }).listen(6666,"localhost",res -> {
      if (res.succeeded()) {
          System.out.println("Server is now listening on actual port: " + server.actualPort());
        } else {
          System.out.println("Failed to bind!");
        }
    });
//    server
//      .listen(6666,"localhost")
//      .onComplete(res -> {
//        if (res.succeeded()) {
//          System.out.println("Server is now listening on actual port: " + server.actualPort());
//        } else {
//          System.out.println("Failed to bind!");
//        }
//      });

  }

}
