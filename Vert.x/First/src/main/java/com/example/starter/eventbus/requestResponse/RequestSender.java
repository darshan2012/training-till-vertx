package com.example.starter.eventbus.requestResponse;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;

public class RequestSender extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    EventBus eb = vertx.eventBus();
    DeliveryOptions options = new DeliveryOptions().setSendTimeout(1);
    vertx.setPeriodic(1000,id->{
      eb.request("data","this is a request",options,res -> {
        if(res.succeeded())
        System.out.println(res.result().body());
        else
          System.out.println(res.cause().getMessage());
      });
    });
  }
}
