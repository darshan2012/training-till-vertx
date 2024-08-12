package socketProgramExample;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getLocalHost();
        byte buf[] =null;
        Scanner sc = new Scanner(System.in);
        while(true){
            String inp = sc.nextLine();
            buf = inp.getBytes();
            DatagramPacket dp = new DatagramPacket(buf,buf.length,ip,4040);
            ds.send(dp);
            if(inp.equals("quit"))
                break;
        }
    }

}
