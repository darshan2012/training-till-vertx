package com.example.starter.zmq.dealer_router;

import org.zeromq.ZMQ;

public class RouterServer {
    public static void main(String[] args) {
        // Create a ZMQ context
        ZMQ.Context context = ZMQ.context(1);

        // Create a ROUTER socket
        ZMQ.Socket router = context.socket(ZMQ.ROUTER);
        router.bind("tcp://127.0.0.1:5555"); // Bind to port

        System.out.println("RouterServer is running...");

        while (true) {
            // Receive the identity of the sender
            byte[] identity = router.recv(0);
            // Receive the message from the sender
            byte[] message = router.recv(0);

            // Print received message
            System.out.println("Received message: " + new String(message));

            // Send a response back to the sender
            router.sendMore(identity);
            router.send("Message received".getBytes(), 0);
        }
    }
}

