package com.example.starter.fileSystem;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.impl.PipeImpl;

public class FileSystemUsage extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    FileSystem fs = vertx.fileSystem();

//    fs.writeFile("/home/darshan/test/tmp.txt",Buffer.buffer("This is the file content.")).onComplete(v -> {
//        if(v.succeeded())
//        {
//            System.out.println("File write done.");
            fs.open("1GBFile.txt",new OpenOptions()).onComplete(res -> {
                if (res.succeeded())
                {
                    AsyncFile file = res.result();
                    ReadStream<Buffer> readStream = file.setReadBufferSize(8192);

                    // Handle data chunks
                    readStream.handler(buffer -> {
                        // Process each chunk of the file
                        System.out.println("Chunk received: " + buffer.toString("UTF-8"));

                        // Perform any processing or writing to another file as needed
                    });

                    // Handle end of the file
                    readStream.endHandler(r -> {
                        System.out.println("Finished reading the file.");
                        // Close the file
                        file.close();
                    });

                    // Handle any errors during reading
                    readStream.exceptionHandler(Throwable::printStackTrace);
                } else {
                    System.err.println("Failed to open file: " + res.cause().getMessage());
                }
            });


//                    for (int i = 0; i < 10; i++) {
//                        Buffer buff = Buffer.buffer(1000);  // New buffer per iteration
//                        file.read(buff, i*10, i*10, 10).onComplete(ar -> {
//                            if (ar.succeeded()) {
//                                System.out.println("Read ok! " + buff.toString());
//                            } else {
//                                System.err.println("Failed to write: " + ar.cause());
//                            }
//                        });
//                    }

//              System.out.println(buff.getString(0,10));
//                }
//                else
//                {
//                    System.out.println(res.cause());
//                }
//            });
//        }
//        else
//        {
//            System.out.println(v.cause());
//        }
//    });


  }
}
