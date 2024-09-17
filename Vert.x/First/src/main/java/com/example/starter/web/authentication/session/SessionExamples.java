package com.example.starter.web.authentication.session;

import com.example.starter.web.authentication.JwtAuthExampleVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class SessionExamples extends AbstractVerticle
{
    @Override
    public void start() throws Exception
    {

    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new JwtAuthExampleVerticle());
    }
}

