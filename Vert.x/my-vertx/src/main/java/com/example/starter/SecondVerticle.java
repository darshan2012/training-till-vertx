package com.example.starter;

import io.vertx.core.AbstractVerticle;

public class SecondVerticle extends AbstractVerticle
{
    @Override
    public void start() throws Exception
    {
        vertx.setPeriodic(1000,id -> {
            System.out.println(Thread.currentThread().getName());
        });
    }
}
