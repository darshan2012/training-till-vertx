package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMsg;


public class MainVerticle extends AbstractVerticle
{

    @Override
    public void start()
    {
        try (ZContext ctx = new ZContext())
        {
            vertx.setPeriodic(5000,
                (id) ->
                {
//            while (true)
//            {
//                try
//                {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e)
//                {
//                    throw new RuntimeException(e);
//                }
                    ZMQ.Socket socket = ctx.createSocket(SocketType.REQ);
                    socket.connect("tcp://10.20.40.150:5555");


//                    ZMsg msg = new ZMsg();
//                    msg.add("one");
//                    msg.add("two");
//                    msg.send(socket);

//                    String request = "Hello";
//                    System.out.println("Req :" + request);
//                    socket.send(request);

                    socket.sendMore( "HELLO");
                    socket.sendMore( "beautiful");
                    socket.send("WORLD!");
                    Buffer response = Buffer.buffer(socket.recv());
                    System.out.println("response :" + response);
//            }

                });
        }

    }
}
