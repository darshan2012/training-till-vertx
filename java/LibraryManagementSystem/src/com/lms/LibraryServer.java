package com.lms;

import java.io.*;
import java.net.*;

import com.lms.models.Borrower;
import com.lms.alternateView.AdminView;
import com.lms.views.BorrowerView;

public class LibraryServer {
    private ServerSocket serverSocket;

    public LibraryServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("Server started on port " + serverSocket.getLocalPort());
        while (true) {
            Socket clientSocket = serverSocket.accept();
            new ClientHandler(clientSocket).start();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;

        public ClientHandler(Socket socket) throws IOException {
            this.clientSocket = socket;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        }

        public void run() {
            try {
                System.out.println("Client connected " + Thread.currentThread().getName());
                out.println("Connected to server");
                String userType = in.readLine();
                if (userType.equals("admin")) {
                    handleAdminOperations();
                } else if (userType.equals("borrower")) {
                    handleBorrowerOperations();
                }
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void handleAdminOperations() throws IOException {
            AdminView adminView = new AdminView(in, out);
            adminView.operations();
        }

        private void handleBorrowerOperations() throws IOException {
            // Assuming you have a method to authenticate and get Borrower object
//            Borrower borrower = authenticateBorrower();
//            if (borrower != null) {
//                BorrowerView borrowerView = new BorrowerView(borrower, in, out);
//                borrowerView.operations();
//            }
        }

//        private Borrower authenticateBorrower() throws IOException {
//            out.println("Enter username:");
//            String username = in.readLine();
//            out.println("Enter password:");
//            String password = in.readLine();
//            return AuthenticationService.signIn(username, password);
//        }
    }

    public static void main(String[] args) {
        try {
            LibraryServer server = new LibraryServer(12345);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
