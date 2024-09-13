package com.example.starter;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.ThreadingModel;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class Main
{

    public static void main(String[] args)
    {
        VertxOptions options = new VertxOptions().setWorkerPoolSize(3);
        Vertx vertx = Vertx.vertx(options);
        try
        {
            Thread.sleep(10000);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        vertx.deployVerticle(SecondVerticle.class.getName());
//        vertx.deployVerticle(SecondVerticle.class.getName(),new DeploymentOptions().setThreadingModel(ThreadingModel.WORKER));
        try
        {
            Thread.sleep(10000);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        vertx.deployVerticle(SecondVerticle.class.getName());
//        vertx.deployVerticle(new Subscriber(),new DeploymentOptions().setThreadingModel(ThreadingModel.WORKER));
//        vertx.deployVerticle(new FileClient());

    }

}
