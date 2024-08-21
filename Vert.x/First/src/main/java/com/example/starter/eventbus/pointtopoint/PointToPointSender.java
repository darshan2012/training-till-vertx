package com.example.starter.eventbus.pointtopoint;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class PointToPointSender extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    EventBus eb = vertx.eventBus();
    vertx.setPeriodic(1000, id -> eb.send("Round-Robin", "only you received this"));
  }
}
