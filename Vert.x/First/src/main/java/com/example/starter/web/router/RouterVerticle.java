package com.example.starter.web.router;

import com.example.starter.json.JsonObjectUsage;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.bridge.BridgeOptions;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.*;
import io.vertx.ext.web.handler.sockjs.SockJSBridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.handler.sockjs.SockJSHandlerOptions;
import io.vertx.ext.web.sstore.LocalSessionStore;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RouterVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    System.out.println("RouterVerticle starting...");
    HttpServer server = vertx.createHttpServer();
    Router router = Router.router(vertx);

    //CORS
    router.route("/*").handler(CorsHandler.create("*"));

    //Logger
    router.route().handler(LoggerHandler.create());

    //Session support
    router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));




    //failureHandler
    router.route().failureHandler(ctx -> {
      ctx.response().setStatusCode(ctx.statusCode()).end("Error: " + ctx.failure().getMessage());
    });

    // Configure the SockJS bridge options
    SockJSHandlerOptions options = new SockJSHandlerOptions()
      .setHeartbeatInterval(2000);

    // Define permitted options for the event bus bridge
    PermittedOptions inboundPermitted = new PermittedOptions().setAddress("chat.to.server");
    PermittedOptions outboundPermitted = new PermittedOptions().setAddress("chat.to.client");

    // Create the SockJS handler
    SockJSHandler sockJSHandler = SockJSHandler.create(vertx, options);
//    sockJSHandler.bridge((SockJSBridgeOptions) new BridgeOptions().addInboundPermitted(inboundPermitted).addOutboundPermitted(outboundPermitted));

    SockJSBridgeOptions bridgeOptions = new SockJSBridgeOptions().addInboundPermitted(inboundPermitted).addOutboundPermitted(outboundPermitted);
    // Mount the SockJS handler to the router
    router
      .route("/eventbus/*")
      .subRouter(sockJSHandler.bridge(bridgeOptions));

    vertx.setPeriodic(1000, t -> vertx.eventBus().publish("chat.to.client", "Message from server every second"));
    vertx.eventBus().consumer("chat.to.server").handler(event -> {
      System.out.println(event.body());
    });

    // /json route
    router.post("/json")
//      .handler(TimeoutHandler.create(100)) // 5 seconds timeout
      .handler(BodyHandler.create())
      .handler(context -> {
        // Offload the blocking operation to a worker thread
        vertx.executeBlocking(promise -> {
          JsonObject obj = context.body().asJsonObject();
          try {
            Thread.sleep(15000); // Simulate blocking operation
          } catch (InterruptedException e) {
            promise.fail(e);
            return;
          }

          // Simulate a long-running computation
          for (int i = 0; i < 1000000000; i++);

          promise.complete(obj);
        }, res -> {
          if (context.response().ended()) {
            return;
          }
          if (res.succeeded()) {
            JsonObject obj = (JsonObject) res.result();
            System.out.println("Received JSON: " + obj);
            context.response().end("done");
          } else {
            context.fail(res.cause());
          }
        });
      });

// Sub-router for /user routes

    router.route("/user/*").subRouter(UserRoutes.routes(vertx));

    // Start the server
    server.requestHandler(router).listen(8080, "0.0.0.0").onComplete(res -> {
      if (res.succeeded()) {
        System.out.println("HTTP server started on port " + res.result().actualPort());
      } else {
        System.err.println("Failed to start HTTP server: " + res.cause().getMessage());
      }
    });
    router.route("/*").handler(ctx -> {
      ctx.fail(404,new Throwable("Route does not exist"));
    });

  }

  public void basicRouterPractice(){
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
      var obj = context.body().asJsonObject();
      var obj2 = obj.getJsonArray("two");
      System.out.println(obj2);
      System.out.println(obj);

      context.response().end("done");
    });
    router.get("/json").handler(ctx -> {
      ctx.fail(404);
    });

    router.route().failureHandler(ctx -> {
      System.out.println(ctx.statusCode());
      ctx.response().setStatusCode(ctx.statusCode()).end("Route not found");
    });
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

    server.requestHandler(router).listen(8088, "0.0.0.0").onComplete(res -> {
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
