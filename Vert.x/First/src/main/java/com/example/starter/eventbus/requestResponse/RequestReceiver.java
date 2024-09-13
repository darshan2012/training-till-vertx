package com.example.starter.eventbus.requestResponse;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;

import java.util.function.Consumer;

public class RequestReceiver extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    EventBus eb = vertx.eventBus();
    MessageConsumer<String> consumer = eb.localConsumer("data");

    consumer.handler(req -> {
      System.out.println("1 " + req.body());
      req.replyAndRequest("This is the response from " + Thread.currentThread().getName(),(res -> {
        System.out.println("3 " + res.result().body());
      }));

    });
    eb.consumer("data",event -> {
      System.out.println("dasdadf" + event.body());
    });
    startPromise.complete();

    vertx.setPeriodic(10000,id -> {
      consumer.unregister();
    });
  }

}
