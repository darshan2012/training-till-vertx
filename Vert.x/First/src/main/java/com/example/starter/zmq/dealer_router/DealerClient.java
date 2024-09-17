package com.example.starter.zmq.dealer_router;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class DealerClient {
    public static void main(String[] args) {
        // Create a ZMQ context
        ZMQ.Context context = ZMQ.context(1);

        // Create a DEALER socket
        ZMQ.Socket dealer = context.socket(ZMQ.DEALER);
        dealer.connect("tcp://127.0.0.1:5555"); // Connect to the ROUTER

        // Send a message to the ROUTER
        dealer.send("DEALER".getBytes(), 0);

        dealer.send("Hello from DEALER".getBytes(), 0);

        // Receive the response from the ROUTER
        byte[] response = dealer.recv(0);
        System.out.println("Received response: " + new String(response));

        // Clean up
        dealer.close();
        context.term();
    }
}
