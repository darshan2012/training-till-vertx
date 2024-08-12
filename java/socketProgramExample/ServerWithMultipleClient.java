package socketProgramExample;

import javax.management.relation.RelationNotFoundException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.zip.InflaterOutputStream;

class ClientHandler implements Runnable {
    private Socket s;
    private DataOutputStream dout;
    private DataInputStream dis;
    private BufferedReader br;
    public ClientHandler(Socket s){
        this.s = s;
        try {
            dout = new DataOutputStream(s.getOutputStream());
            dis = new DataInputStream(s.getInputStream());
            br = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " connected.");
        while(true){
            String msg = null;
            try {
                msg = (String)dis.readUTF();
                System.out.println(Thread.currentThread().getName() + " :" + msg);

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
            } catch (IOException e) {
                System.out.println(Thread.currentThread().getName() + " Exited.");
                break;
            }
        }
        try {
            s.close();
            dout.close();
            br.close();
            dis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
public class ServerWithMultipleClient {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(6666);

        while(true){
            Socket s = ss.accept();
            new Thread(new ClientHandler(s)).start();
        }
    }
}
