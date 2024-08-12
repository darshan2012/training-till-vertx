package socketProgramExample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


class TCPServer {
   public static void main(String[]args) throws IOException {
       ServerSocket ss = new ServerSocket(6666);
       System.out.println("Server running on port 6666...");
       Socket s = ss.accept();

       System.out.println("Client connected.");
       DataInputStream dis = new DataInputStream(s.getInputStream());
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       DataOutputStream dout;

       while(true){
           String msg = (String)dis.readUTF();
           System.out.println("Client :" + msg);
           dout = new DataOutputStream(s.getOutputStream());
           if(msg.equals("quit"))
           {
               msg = "quiting...";
           }
           else
               msg = br.readLine();
           dout.writeUTF(msg);
           dout.flush();
           if(msg.equals("quiting..."))
               break;
       }
       dout.close();
       ss.close();
   }


}
