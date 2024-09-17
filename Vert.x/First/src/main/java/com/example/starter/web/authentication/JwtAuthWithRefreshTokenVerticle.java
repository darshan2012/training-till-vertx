package com.example.starter.web.authentication;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.auth.JWTOptions;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.JWTAuthHandler;
import io.vertx.core.Handler;

public class JwtAuthWithRefreshTokenVerticle extends AbstractVerticle {

    @Override
    public void start() {
        // Create JWT Auth provider with a shared secret
        JWTAuth jwtAuth = JWTAuth.create(vertx, new JWTAuthOptions()
            .addJwk(new JsonObject()
                .put("alg", "HS256")
                .put("kty", "oct")
                .put("k", "secret-key")));  // Use your secret key here

        Router router = Router.router(vertx);

        // Handle POST requests with body
        router.route().handler(BodyHandler.create());

        // Login route to generate access and refresh tokens
        router.post("/login").handler(handleLogin(jwtAuth));

        // Refresh token route to generate new access token
        router.get("/refresh").handler(handleRefreshToken(jwtAuth));

        // Protect the route with access token
        router.route("/protected/*").handler(JWTAuthHandler.create(jwtAuth));

        // Define protected route
        router.get("/protected/hello").handler(this::handleProtected);

        vertx.createHttpServer().requestHandler(router).listen(8080);
    }

    // Handle login to generate access and refresh tokens
    private Handler<RoutingContext> handleLogin(JWTAuth jwtAuth) {
        return ctx -> {
            JsonObject body = ctx.getBodyAsJson();
            String username = body.getString("username");
            String password = body.getString("password");

            // Simple authentication (replace it with actual user validation logic)
            if ("user".equals(username) && "pass".equals(password)) {
                // Create an access token (short-lived)
                String accessToken = jwtAuth.generateToken(new JsonObject().put("sub", "user"),
                    new JWTOptions().setExpiresInMinutes(1));

                // Create a refresh token (long-lived)
                String refreshToken = jwtAuth.generateToken(new JsonObject().put("sub", "user").put("type", "refresh"),
                    new JWTOptions().setExpiresInMinutes(5));

                ctx.response().addCookie(Cookie.cookie("refreshToken",refreshToken).setHttpOnly(true));

                // Return both tokens
                ctx.response()
                    .putHeader("Content-Type", "application/json")
                    .end(new JsonObject()
                        .put("accessToken", accessToken)
//                        .put("refreshToken", refreshToken)
                        .encode());
            } else {
                ctx.response().setStatusCode(401).end("Invalid credentials");
            }
        };
    }

    // Handle refresh token to generate a new access token
    private Handler<RoutingContext> handleRefreshToken(JWTAuth jwtAuth) {
        return ctx -> {
            Cookie cookie = ctx.request().getCookie("refreshToken");

            String refreshToken = cookie.getValue();
//            String refreshToken = body.getString("refreshToken");
            System.out.println(refreshToken);
            // Validate the refresh token

            jwtAuth.authenticate(new JsonObject().put("token", refreshToken), res -> {
                if (res.succeeded()) {
                    JsonObject claims = res.result().attributes().getJsonObject("accessToken");
//                    System.out.println(claims);
                    if ("refresh".equals(claims.getString("type"))) {
                        // Generate new access token
                        String newAccessToken = jwtAuth.generateToken(new JsonObject().put("sub", claims.getString("sub")),
                            new JWTOptions().setExpiresInMinutes(1));  // New access token valid for 15 minutes
                        ctx.response()
                            .putHeader("Content-Type", "application/json")
                            .end(new JsonObject().put("accessToken", newAccessToken).encode());
                    } else {
                        System.out.println(claims.getString("type"));
                        ctx.response().setStatusCode(401).end("Invalid refresh token");
                    }
                } else {
                    System.out.println(res.cause());
                    ctx.response().setStatusCode(401).end("here Invalid refresh token");
                }
            });
        };
    }

    // Protected endpoint (access token required)
    private void handleProtected(RoutingContext ctx) {
        ctx.response().end("Access to protected resource granted!");
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new JwtAuthWithRefreshTokenVerticle());
    }
}
