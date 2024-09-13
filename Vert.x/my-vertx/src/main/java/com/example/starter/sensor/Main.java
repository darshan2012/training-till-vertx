package com.example.starter.sensor;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class Main {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(HeatSensor.class.getName(), new DeploymentOptions().setInstances(4));
    vertx.deployVerticle(new Listener());
    vertx.deployVerticle(new SensorData());
    vertx.deployVerticle(new HttpServer());
  }
}
