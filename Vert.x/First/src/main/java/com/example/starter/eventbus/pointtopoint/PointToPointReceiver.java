package com.example.starter.eventbus.pointtopoint;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class PointToPointReceiver  extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    EventBus eb = vertx.eventBus();
    eb.consumer("Round-Robin",data -> {
      System.out.println(data.body());
//      data.reply("Data received");
    });
  }
}
