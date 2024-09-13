package com.example.starter.zmq;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMsg;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

public class ZMQServerVerticle extends AbstractVerticle
{
    @Override
    public void start() throws Exception
    {
        vertx.executeBlocking(promise ->
        {
            try (ZContext ctx = new ZContext())
            {
                ZMQ.Socket socket;
                socket = ctx.createSocket(SocketType.REP);
                socket.bind("tcp://10.20.40.150:5555");
                while (true)
                {
//                  Buffer buf = Buffer.buffer(socket.recv());
                    ZMsg request = ZMsg.recvMsg(socket);
                    System.out.println(ZMQ.getVersionString());
                    System.out.println(ZMQ.getFullVersion());
                    System.out.println("Received : " + request.pollLast());

                    String response = "world";

                    socket.send(request + " " + response);
                }
            }

        });


    }


}
