package com.example.starter.http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.example.core.http.simple.ClientVerticle;

public class HttpVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    vertx.deployVerticle(new HttpServerVerticle()).onComplete(res -> {
      if (res.succeeded()) {
        System.out.println("Server Deployed Successfully");
        vertx.deployVerticle(new ClientVerticle());
      } else {
        System.out.println("Server is not deployed");
      }
    });
  }
}
