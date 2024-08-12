

import java.io.*;
import java.net.Socket;

 class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost",4040);
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        DataInputStream dis;
        while (true) {

            String msg = br.readLine();
            dout.writeUTF(msg);
            dout.flush();

            dis = new DataInputStream(s.getInputStream());
            String server = (String)dis.readUTF();
            System.out.println("Server :" + server);
            if(msg.equals("quit"))
                break;
        }
        dout.close();

        s.close();
    }

}
