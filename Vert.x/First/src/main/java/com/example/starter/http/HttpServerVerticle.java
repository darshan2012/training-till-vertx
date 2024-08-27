package com.example.starter.http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;

public class HttpServerVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
//    default
//    HttpServer server = vertx.createHttpServer();

    HttpServerOptions options = new HttpServerOptions();
    HttpServer server = vertx.createHttpServer();
    server.invalidRequestHandler(req -> {
      System.out.println(req.path() + " in invalid request handler");
      if (req.path().equals("/home")) {
        req.response().setStatusCode(500).end("Invalid request");
      }
    }).requestHandler(req -> {
      System.out.println(req.uri());
      System.out.println(req.params().get("a"));
      System.out.println(req.path());
      System.out.println(req.method());
      System.out.println(req.version());
      System.out.println("Query " + req.query());
      System.out.println(req.absoluteURI());

      //reading data from request body
      req.handler(buffer -> {
        System.out.println("received " + buffer.length());
      });
      req.response().putHeader("content-type", "text/plain").end("Hello from server");
    }).listen(8080).onComplete(res -> {
      if (res.succeeded()) {
//        startPromise.complete();
        System.out.println("Server is now Listening! " + res.result().actualPort());
      } else {
//        startPromise.fail("Server Error");
        System.out.println("Failed to bind");
      }
    });
  }
}
