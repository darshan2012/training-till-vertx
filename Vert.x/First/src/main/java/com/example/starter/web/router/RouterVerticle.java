package com.example.starter.web.router;

import com.example.starter.json.JsonObjectUsage;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RouterVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    System.out.println("RouterVerticle starting...");
    HttpServer server = vertx.createHttpServer();
    Router router = Router.router(vertx);
    router.get("/getTime").handler(req -> {
      req.response().putHeader("content-type","Application/json").setStatusCode(200).end(new JsonObject().put("Time",LocalDateTime.now().toString()).encode());
    });
    router
      .route(HttpMethod.GET, "/getTime/:from-:to")
      .handler(ctx -> {

        String from = ctx.pathParam("from"); // AMS
        String to = ctx.pathParam("to"); // SFO
        // remember that this will not work as expected when the parameter
        // naming pattern in use is not the "extended" one. That is because in
        // that case "-" is considered to be part of the variable name and
        // not a separator.
        ctx.response().end(from + " - " + to);
      });
    router.get("/getTime/:day").handler(ctx -> {
      String s = ctx.pathParam("day");
      System.out.println(s);
      ctx.response().end(LocalDate.now().getDayOfWeek().toString());
    });

    router.post("/json").handler(BodyHandler.create()).handler(context -> {
      context.body();
    })


    /* rerouting */
//    router.get("/first").handler(ctx -> {
//      ctx.response().setChunked(true);
//      System.out.println("In first ");
////      ctx.response().write("this is from first");
//      try {
//
//        ctx.reroute("/second");
//      } catch (Exception e) {
//        System.out.println(e.getMessage());
//      }
//    });
//    router.get("/second").handler(ctx -> {
//      System.out.println("In second delete");
//      ctx.response().end("Deleted something!");
//    });

    server.requestHandler(router).listen(8080, "0.0.0.0").onComplete(res -> {
      if (res.succeeded()) {
        System.out.println("HTTP server started on port " + res.result().actualPort());
      } else {
        System.err.println("Failed to start HTTP server: " + res.cause().getMessage());
      }
    });

//    routeOrderExample();

  }
  public void routeOrderExample(){
    Router router = Router.router(vertx);
    router
      .route("/some/path/")
      .handler(ctx -> {
        System.out.println("Handling /some/path/ route1");
        HttpServerResponse response = ctx.response();
        response.write("route1\n");
        ctx.next();
      }).last();

    router
      .route("/some/path/")
      .order(0)
      .handler(ctx -> {
        System.out.println("Handling /some/path/ route2");
        HttpServerResponse response = ctx.response();
        response.setChunked(true);
        response.write("route2\n");
        ctx.next();
      });

    router
      .route("/some/path/")
      .order(2)
      .handler(ctx -> {
        System.out.println("Handling /some/path/ route3");
        HttpServerResponse response = ctx.response();
        response.write("route3");
        ctx.response().end();
      });
  }

}
