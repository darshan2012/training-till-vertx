package com.example.starter.eventbus.pointtopoint;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;

public class PointToPointSender extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    EventBus eb = vertx.eventBus();
    vertx.setPeriodic(1000, id -> eb.send("Round-Robin", "only you received this"));

    eb.<JsonObject>localConsumer("json",res -> {
          System.out.println("1. " + res.body());
          JsonObject item = res.body();
          item.put("asdk","akjfs");
      });
      JsonObject json = new JsonObject().put("s","s");
      eb.send("json",json);

      vertx.setPeriodic(1000,id -> {
          System.out.println(json);
      });
//      eb.consumer("Round-Robin",res -> {
//          System.out.println("2. " + res.body());
//      });
//      eb.consumer("Round-Robin",res -> {
//          System.out.println("3. " + res.body());
//      });
//      eb.consumer("Round-Robin",ctx -> {
//          System.out.println(ctx.body());
//          ctx.reply(ctx.body());
//      });
//      HashSet set = new HashSet();
//      set.add("hello");
//      set.add("world");
//
//      Queue<String> arr = new ArrayDeque<>();
//      arr.add("aaa");
//      arr.add("abb");
//      JsonObject json = new JsonObject().put("arr",arr);
//      System.out.println(json);
//      eb.request("Round-Robin", json,res -> {
//          System.out.println(res.result().body());
//          vertx.close();
//      });

//      eb.consumer("Round-Robin",res -> {
//          System.out.println("4. " + res.body());
//      });

  }
}
