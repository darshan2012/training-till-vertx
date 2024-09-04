package com.example.starter.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;

import java.time.LocalDateTime;

public class StandardVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    Context context1 = vertx.getOrCreateContext();
    context1.put("Global","data is global");
    context1.putLocal("local","data is local");

    vertx.sharedData().getLocalMap("myMap").put("GlobalShared", "data is global via sharedData");
    Thread.sleep(1000);
//    JsonObject config = new JsonObject().put("one",1).put("two",2);
//    DeploymentOptions options = new DeploymentOptions().setConfig(config);
//    vertx.deployVerticle(new ConfigVerticle(),options).onComplete((res) -> {
//      System.out.println("Deployment done " + res + " at " + LocalDateTime.now());
//    });
//    vertx.runOnContext((id) -> {
//      System.out.println((String) context1.getLocal("local"));
//      System.out.println((String) context1.get("Global"));
//    });
//    System.out.println((String) context1.getLocal("local"));

  }
}
