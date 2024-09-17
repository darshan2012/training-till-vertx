package com.example.starter.web.authentication;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.auth.JWTOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.JWTAuthHandler;

public class JwtAuthExampleVerticle extends AbstractVerticle {

    @Override
    public void start() {
        // Create JWT Auth provider
        JWTAuth jwtAuth = JWTAuth.create(vertx, new JWTAuthOptions()
            .addJwk(new JsonObject()
                .put("alg", "HS256")
                .put("kty", "oct")
                .put("k", "secret-key")));  // Use your secret key here

        Router router = Router.router(vertx);

        // BodyHandler to handle POST requests with body
        router.route().handler(BodyHandler.create());

        // Unprotected route for login (generating JWT)
        router.post("/login").handler(handleLogin(jwtAuth));

        // Protected route
        router.route("/protected/*").handler(JWTAuthHandler.create(jwtAuth));

        // Define protected route
        router.get("/protected/hello").handler(this::handleProtected);

        vertx.createHttpServer().requestHandler(router).listen(8080).onComplete(res -> {
            if (res.succeeded())
            {
                System.out.println("Server started on port " + res.result().actualPort());
            }
        });
    }

    // Handle login and generate JWT
    private Handler<RoutingContext> handleLogin(JWTAuth jwtAuth) {
        return ctx -> {
            JsonObject body = ctx.getBodyAsJson();
            String username = body.getString("username");
            String password = body.getString("password");

            // Simple authentication (you can replace it with real user validation logic)
            if ("user".equals(username) && "pass".equals(password)) {
                // Create a JWT token
                String token = jwtAuth.generateToken(new JsonObject().put("sub", "user"), new JWTOptions().setExpiresInMinutes(60));
                ctx.response()
                    .putHeader("Content-Type", "application/json")
                    .end(new JsonObject().put("token", token).encode());
            } else {
                ctx.response().setStatusCode(401).end("Invalid credentials");
            }
        };
    }

    // Protected endpoint
    private void handleProtected(RoutingContext ctx) {
        System.out.println(ctx.user());
        System.out.println(ctx.user().principal());
        System.out.println(ctx.user().principal().getString("sub"));
        ctx.response().end("Access to protected resource!");
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new JwtAuthExampleVerticle());
    }
}
