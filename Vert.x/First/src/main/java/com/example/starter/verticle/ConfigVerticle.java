package com.example.starter.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Promise;

public class ConfigVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
//    Thread.sleep(10000);
    Context context1 = vertx.getOrCreateContext();
    System.out.println((String) context1.get("Global"));
    System.out.println(context1.deploymentID() + " " + context1.config());
    System.out.println(config());

    String globalData = vertx.sharedData().getLocalMap("myMap").get("GlobalShared").toString();
    System.out.println("Here in config verticle " + globalData);
//    startPromise.complete();
//    vertx.close();
  }
}
