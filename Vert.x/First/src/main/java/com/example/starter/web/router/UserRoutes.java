package com.example.starter.web.router;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.util.*;

public class UserRoutes {
    private final Map<String,User> users = new HashMap<>();

    public static Router routes(Vertx vertx){
      Router router = Router.router(vertx);
      System.out.println("here");
      router.post("/").handler(UserRoutes::addUser);
      router.get("/:id").handler(UserRoutes::getUser);
//      NavigableMap<String,String> m = new TreeMap<>();
      System.out.println("here as well");
      return router;
    }
    public static void addUser(RoutingContext ctx){

      ctx.response().end("User added!!");
    }
    public static void getUser(RoutingContext ctx){
      ctx.response().end("User with username " + ctx.pathParam("id") + " fetched!!");
    }
}
