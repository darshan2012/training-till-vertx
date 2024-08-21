package com.example.starter.dnsClient;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.dns.DnsClient;
import io.vertx.core.dns.MxRecord;

import java.util.List;

public class DNSClientUsage extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    DnsClient client = vertx.createDnsClient(53,"8.8.8.8");
//    client.lookup("vertx.io").onComplete(res -> {
//      if (res.succeeded()) {
//        System.out.println(res.result());
//      } else {
//        System.out.println(res.cause());
//      }
//    });

    client
      .resolveMX("google.com")
      .onComplete(ar -> {
        if (ar.succeeded()) {
          List<MxRecord> records = ar.result();
          for (var record : records) {
            System.out.println(record);
          }
          System.out.println("done");
        } else {
          System.out.println("Failed to resolve entry" + ar.cause());
        }
      });

  }
}
