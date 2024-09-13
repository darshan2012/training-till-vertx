package com.example.starter.eventbus.pubsub;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.impl.headers.HeadersAdaptor;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Sender extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    EventBus eb = vertx.eventBus();
//    vertx.setPeriodic(1000,id -> eb.publish("data","new data"));
    //Only one of the receiver will receive in round-robin manner
    HashSet<String> s = new HashSet<>();
    s.add("one");
    s.add("two");
    s.add("three");
    DeliveryOptions options = new DeliveryOptions();
    options.addHeader("header","header-value");
    JsonObject json = JsonObject.of("fist","1","two",2,"three",3L,"four",4.0);
    json.put("set",s);

//    System.out.println(options.getSendTimeout());
//    System.out.println(options.toJson());
    A a = new A(10);
//    JsonArray arr = JsonArray.of(JsonObject.of("one",a),JsonObject.of("two",a));
      HashMap<String,String> map = new HashMap<>();

       map.put("a","A");
      map.put("b","B");
      json.put("object",a);
    vertx.setPeriodic(1000, id -> eb.publish("json",new JsonObject().put("map","11")));

    //    eb.publish("json",json,options);
  }

}
