package com.example.starter.eventbus.pubsub;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;

public class Sender extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    EventBus eb = vertx.eventBus();
//    vertx.setPeriodic(1000,id -> eb.publish("data","new data"));
    //Only one of the receiver will receive in round-robin manner

//    vertx.setPeriodic(1000, id -> eb.publish("data", "new data"));
    DeliveryOptions options = new DeliveryOptions();
    options.addHeader("header","header-value");
    eb.publish("data","data with header",options);
  }

}
