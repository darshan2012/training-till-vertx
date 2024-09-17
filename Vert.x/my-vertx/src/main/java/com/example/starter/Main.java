package com.example.starter;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.ThreadingModel;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class Main
{

    public static void main(String[] args)
    {
        VertxOptions options = new VertxOptions().setEventLoopPoolSize(1);
        Vertx vertx = Vertx.vertx(options);

        vertx.deployVerticle(SecondVerticle.class.getName());
//        vertx.deployVerticle(SecondVerticle.class.getName(),new DeploymentOptions().setThreadingModel(ThreadingModel.WORKER));
        vertx.deployVerticle(SecondVerticle.class.getName());
//        vertx.deployVerticle(new Subscriber(),new DeploymentOptions().setThreadingModel(ThreadingModel.WORKER));
//        vertx.deployVerticle(new FileClient());

    }

}
