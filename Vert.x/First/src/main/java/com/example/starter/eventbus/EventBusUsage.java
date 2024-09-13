package com.example.starter.eventbus;

import com.example.starter.eventbus.pointtopoint.PointToPointReceiver;
import com.example.starter.eventbus.pubsub.Receiver;
import com.example.starter.eventbus.pubsub.Sender;
import com.example.starter.eventbus.pointtopoint.PointToPointSender;
import com.example.starter.eventbus.requestResponse.RequestReceiver;
import com.example.starter.eventbus.requestResponse.RequestSender;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.eventbus.EventBus;

public class EventBusUsage extends AbstractVerticle {
  @Override
  public void start() throws Exception {
//      vertx.deployVerticle(new Sender());
//    vertx.deployVerticle(new Receiver());
      //      vertx.deployVerticle(Receiver.class.getName(),new DeploymentOptions().setInstances(3));
    vertx.deployVerticle(new PointToPointSender());
//    vertx.deployVerticle(PointToPointReceiver.class.getName(),new DeploymentOptions().setInstances(4));
//    vertx.deployVerticle(RequestSender.class.getName());
//    vertx.deployVerticle(RequestReceiver.class.getName()).onComplete(v -> {
//      vertx.deployVerticle(RequestSender.class.getName());
//
//    });
  }
}
