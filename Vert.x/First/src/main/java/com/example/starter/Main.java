package com.example.starter;

import io.vertx.core.Vertx;

public class Main
{

    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();



        vertx.deployVerticle(MainVerticle.class.getName());
//        vertx.deployVerticle(new PublisherVerticle());
    }
}
