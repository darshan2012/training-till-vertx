    package com.example.starter.socket;


    import io.vertx.core.AbstractVerticle;
    import io.vertx.core.buffer.Buffer;
    import io.vertx.core.net.NetClient;

    public class FileClient extends AbstractVerticle {

        @Override
        public void start() throws Exception {
            // Create a TCP client
            NetClient client = vertx.createNetClient();

            // Connect to the server on localhost:1234
            client.connect(1234, "localhost", res -> {
                if (res.succeeded()) {
                    System.out.println("Connected to server!");

                    // Get the socket
                    var socket = res.result();

                    // Handle incoming file data
                    socket.handler(buffer -> {
                        System.out.println("Received chunk: " + buffer.length() + " bytes");
                        // You can process or store the received buffer here
                    });

                    // Handle the end of the file transfer
                    socket.endHandler(v -> {
                        System.out.println("Finished receiving the file.");
                        socket.close();
                    });

                    // Handle any errors
                    socket.exceptionHandler(Throwable::printStackTrace);

                } else {
                    System.err.println("Failed to connect: " + res.cause().getMessage());
                }
            });
        }
    }
