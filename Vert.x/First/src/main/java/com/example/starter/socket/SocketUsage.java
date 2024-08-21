package com.example.starter.socket;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.net.NetServer;

public class SocketUsage extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    vertx.deployVerticle(new TCPServer());

  }
}
