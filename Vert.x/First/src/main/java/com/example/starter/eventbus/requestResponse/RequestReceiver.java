package com.example.starter.eventbus.requestResponse;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;

import java.util.function.Consumer;

public class RequestReceiver extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    EventBus eb = vertx.eventBus();
    MessageConsumer<String> consumer = eb.consumer("data");

    consumer.handler(req -> {
      System.out.println(req.body());
      req.reply("This is the response from " + Thread.currentThread().getName());
    });

    vertx.setPeriodic(10000,id -> {
      consumer.unregister();
    });
  }

}
