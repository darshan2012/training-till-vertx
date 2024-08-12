package socketProgramExample;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(3333);
        byte[] rcv = new byte[65535];
        DatagramPacket dp = null;
        while(true)
        {
            dp = new DatagramPacket(rcv,rcv.length);
            ds.receive(dp);
            String msg = String.valueOf(data(rcv));
            System.out.println("Client : " + msg);
            if(msg.equals("quit"))
            {
                System.out.println("Exiting");
                break;
            }
            rcv = new byte[65535];
        }
    }
    public static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}
