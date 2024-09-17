package com.example.starter.web.router;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class SockJSServerVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(SockJSServerVerticle.class);

    @Override
    public void start() {
        // Create a router object
        Router router = Router.router(vertx);
//        LOGGER.info("aklsdfkfj");
        // Serve index.html when accessing the root path
        router.route("/").handler(ctx -> {
            ctx.response().sendFile("client.html").onFailure(err -> {
                LOGGER.error("Failed to send index.html: " + err.getMessage());
                ctx.fail(404); // Return 404 if the file is not found
            });
        });

        // Create and configure SockJS handler
        SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
        router.route("/myapp/*").handler(sockJSHandler);

        // Handle incoming SockJS messages
        sockJSHandler.socketHandler(sockJSSocket -> {
            sockJSSocket.handler(buffer -> {
                String message = buffer.toString();
                LOGGER.info("Received message: " + message);

                // Reply back to the client
                sockJSSocket.write("Message received: " + message);
            });
        });

        // Create and configure the HTTP server
        HttpServerOptions options = new HttpServerOptions().setPort(8080);
        HttpServer server = vertx.createHttpServer(options);

        // Start the HTTP server and bind it to port 8080
        server.requestHandler(router).listen(result -> {
            if (result.succeeded()) {
                LOGGER.info("HTTP server started on port 8080");
            } else {
                LOGGER.error("Failed to start HTTP server: " + result.cause().getMessage());
            }
        });
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new SockJSServerVerticle(), res -> {
            if (res.succeeded()) {
                System.out.println("SockJSServerVerticle deployment complete.");
            } else {
                System.out.println("SockJSServerVerticle deployment failed: " + res.cause().getMessage());
            }
        });
    }
}
