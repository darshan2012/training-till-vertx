package com.example.starter.eventbus.requestResponse;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;

public class RequestSender extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    EventBus eb = vertx.eventBus();
    DeliveryOptions options = new DeliveryOptions().setSendTimeout(100);
    eb.request("data","this is a request",options,res -> {
      if(res.succeeded())
      {

        System.out.println("2 " + res.result().body());
        res.result().reply("This is final reply");
      }
      else
        System.out.println("hello " + res.cause().getMessage());
    });


  }
}
