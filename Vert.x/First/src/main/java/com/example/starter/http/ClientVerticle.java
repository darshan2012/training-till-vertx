package io.vertx.example.core.http.simple;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpMethod;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class ClientVerticle extends AbstractVerticle {


  @Override
  public void start() throws Exception {
    HttpClient client = vertx.createHttpClient();
    client.request(HttpMethod.GET, 8080, "localhost", "/")
      .compose(req -> req.send()
        .compose(resp -> {
          System.out.println("Got response " + resp.statusCode());
          return resp.body();
        })).onSuccess(body -> {
        System.out.println("Got data " + body.toString("ISO-8859-1"));
      }).onFailure(Throwable::printStackTrace);
  }
}
