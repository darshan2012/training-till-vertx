package com.example.starter.zmq.pubsub;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.StringTokenizer;

public class Subscriber extends AbstractVerticle
{

    @Override
    public void start() throws Exception
    {
//        vertx.<Void>executeBlocking(this::subscribeToUpdates, result -> {
//            if (result.succeeded()) {
//                System.out.println("Subscription task completed successfully.");
//            } else {
//                System.err.println("Failed to complete subscription task: " + result.cause());
//            }
//        });
        subscribeToUpdates(Promise.promise());
    }

    private void subscribeToUpdates(Promise<Void> promise)
    {
        try (ZContext context = new ZContext())
        {
            // Socket to talk to server
            System.out.println("Collecting updates from weather server");
            ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);
            subscriber.bind("tcp://localhost:5555");

            // Subscribe to zipcode, default is NYC, 10001
            String filter = "10001 ";
            subscriber.subscribe(filter.getBytes(ZMQ.CHARSET));
            subscriber.subscribe("10002");
            System.out.println("here");
            // Process 100 updates
            int updateCount = 100;
            long totalTemp = 0;
            for (int i = 0; i < updateCount; i++)
            {
                // Use trim to remove the trailing '0' character
                String receivedMessage = subscriber.recvStr(0).trim();
                System.out.println("received: " + receivedMessage);

                StringTokenizer tokenizer = new StringTokenizer(receivedMessage, " ");
                int zipcode = Integer.parseInt(tokenizer.nextToken());
                int temperature = Integer.parseInt(tokenizer.nextToken());
                int relhumidity = Integer.parseInt(tokenizer.nextToken());

                totalTemp += temperature;
            }

            System.out.println(
                String.format(
                    "Average temperature for zipcode '%s' was %d.",
                    filter,
                    (int) (totalTemp / updateCount)
                )
            );

            promise.complete();
        } catch (Exception e)
        {
            promise.fail(e);
        }
    }
}
