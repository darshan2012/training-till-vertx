package com.example.starter.socket;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.net.NetServer;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.Pump;

public class FileServer extends AbstractVerticle
{

    @Override
    public void start() throws Exception
    {

        NetServer server = vertx.createNetServer();

        server.connectHandler(socket ->
        {
            FileSystem fs = vertx.fileSystem();


            fs.open("1GBFile.txt", new OpenOptions()).onComplete(res ->
            {
                if (res.succeeded())
                {

                    AsyncFile file = res.result();

                    ReadStream<Buffer> readStream = file.setReadBufferSize(4096);

                    Pump.pump(readStream, socket).start();

//                    readStream.pipeTo(socket);

                    readStream.endHandler(v ->
                    {

                        System.out.println("Finished sending the file.");

                        file.close();

                        socket.close();
                    });

                    readStream.exceptionHandler(Throwable::printStackTrace);

                } else
                {

                    System.err.println("Failed to open file: " + res.cause().getMessage());

                    socket.close();
                }
            });

        }).listen(5555, ar ->
        {
            if (ar.succeeded())
            {

                System.out.println("Server is now listening on port " + ar.result().actualPort());

            } else
            {

                System.err.println("Failed to bind: " + ar.cause().getMessage());

            }
        });
    }
}
