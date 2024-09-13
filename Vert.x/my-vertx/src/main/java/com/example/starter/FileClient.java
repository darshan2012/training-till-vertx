package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.net.NetClient;

public class FileClient extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        NetClient client = vertx.createNetClient();
        FileSystem fs = vertx.fileSystem();

        // Open or create a file to save the received data
        fs.open("/home/darshan/test/Received1GBFile.txt", new OpenOptions().setWrite(true).setCreate(true)).onComplete(fileResult -> {
            if (fileResult.succeeded()) {
                AsyncFile asyncFile = fileResult.result();

                // Connect to the server
                client.connect(5555, "localhost", res -> {
                    if (res.succeeded()) {
                        System.out.println("Connected to server!");

                        var socket = res.result();

                        socket.handler(buffer -> {
                            System.out.println("Received chunk: " + buffer.length() + " bytes");


                            asyncFile.write(buffer);
                        });


                        socket.endHandler(v -> {
                            System.out.println("Finished receiving the file.");
                            asyncFile.close();
                            socket.close();
                        });


                        socket.exceptionHandler(Throwable::printStackTrace);
                    } else {
                        System.err.println("Failed to connect: " + res.cause().getMessage());
                    }
                });
            } else {
                System.err.println("Failed to open file for writing: " + fileResult.cause().getMessage());
            }
        });
    }
}
