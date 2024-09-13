package com.example.starter.json;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.pointer.JsonPointer;


import java.util.HashMap;
import java.util.Map;

public class JsonObjectUsage extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    waysToCreate();
    gettingValue();
    jsonArrayExmaple();
  }
  public void waysToCreate(){
    creatingFromString();
    creatingFromMap();

    Directly();
  }
  public void creatingFromString(){
    String jsonString = "{\"key\":\"value\"}";
    JsonObject json = new JsonObject(jsonString);
  }
  public void creatingFromMap(){
    Map<String,Object> m = new HashMap<>();
    m.put("key","value");

    JsonObject json = new JsonObject(m);

  }
  public void Directly(){
    JsonObject json = new JsonObject();
    json.put("Key","value").put("one",1).put("flag",true).put("null",null);
  }
  public void gettingValue(){
    JsonObject json = new JsonObject();
    json.put("Key","value").put("one",1).put("flag",true).put("null",null);
    String val = json.getString("Key");
    Integer i = json.getInteger("one");
    Boolean b = json.getBoolean("flag");
    System.out.println("value is " + val);

  }
  public void jsonArrayExmaple(){
    JsonArray arr = new JsonArray();
    arr.add("one");
    arr.add(1);
    arr.add(true);
    arr.add(null);
    arr.add(new JsonObject().put("One",1));
    System.out.println(arr.getString(0));
    System.out.println(arr.getInteger(1));
    System.out.println(arr.getJsonObject(4).encode());
    vertx.runOnContext(v -> {
      for(int i=0;i<arr.size();i++)
        System.out.println(arr.getValue(i));
      System.out.println("Context :" + vertx.getOrCreateContext().deploymentID() + " " + Thread.currentThread().getName());
    });
    System.out.println("Task after this");
    System.out.println("Context outside :" + vertx.getOrCreateContext().deploymentID() + " " + Thread.currentThread().getName());

    System.out.println(arr instanceof JsonArray);
//    Object object = Json.decodeValue((Buffer)arr);
//    if(arr instanceof JsonArray)
//      System.out.println("JsonArray");
//    else if(object instanceof JsonObject)
//      System.out.println("JsonObject");
//    else if(object instanceof String)
//      System.out.println("JsonString");
//    else
//      System.out.println("Nothing");
  }
  public void jsonPointerExamples(){
    JsonPointer jp = JsonPointer.from("/first/second");
    JsonPointer jp2 = JsonPointer.create();
    jp2.append(2);
    jp2.append("Sdsd");

  }
}
