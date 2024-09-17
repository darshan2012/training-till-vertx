package com.example.starter.eventbus.pointtopoint;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;

public class PointToPointReceiver  extends AbstractVerticle {
  @Override

  public void start() throws Exception {

      EventBus eb = vertx.eventBus();

      eb.<JsonObject>localConsumer("json", data -> {

        var item = data.body();

        item.put("addd","enw");

        System.out.println(data.body() + " in consumer");

//      data.reply("Data received");

      });
  }
}
