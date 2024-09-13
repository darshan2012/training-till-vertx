package com.example.starter.zmq;

import io.vertx.core.AbstractVerticle;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.Random;

public class PublisherVerticle extends AbstractVerticle {

    private ZMQ.Socket socket;
    private ZContext context;

    @Override
    public void start() throws Exception {
        // Initialize ZeroMQ context and socket
        context = new ZContext();
        socket = context.createSocket(SocketType.PUB);

        // Bind to TCP and IPC endpoints
        socket.connect("tcp://localhost:5555");
        socket.bind("ipc://weather");

        Random random = new Random();

        // Use Vert.x periodic timer to send updates every second
        vertx.setPeriodic(1, id -> {
            int zipcode = 10000 + random.nextInt(10000);

            int temperature = random.nextInt(215) - 80;

            int relhumidity = random.nextInt(50) + 10;

            // Prepare the weather update message
            String update = String.format("%05d %d %d", 10001, temperature, relhumidity);

            socket.send(update);  // Send the message to subscribers

            System.out.println("Published: " + update);
        });
    }

    @Override
    public void stop() throws Exception {
        // Clean up the socket and context when the verticle stops
        if (socket != null) {
            socket.close();
        }
        if (context != null) {
            context.close();
        }
    }
}
