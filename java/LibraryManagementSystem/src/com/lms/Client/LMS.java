package com.lms.Client;

import com.lms.alternateView.AdminView;
import com.lms.alternateView.BorrowerView;
import com.lms.models.Borrower;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class LMS {
    private BufferedReader br;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public LMS(String host, int port) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void authChoices() {
        System.out.println("\n1. Login\n2. Registration\n3. Exit\n");
    }

    private void displayHomeHeader() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("+-------------------------------------------+");
        System.out.println("|                                           |");
        System.out.println("| ======> Library Management System <====== |");
        System.out.println("|                                           |");
        System.out.println("+-------------------------------------------+");
        System.out.println();
    }

    public void start() {
        displayHomeHeader();
        int choice = 0;
        do {
            try {
                authChoices();
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(br.readLine());
                switch (choice) {
                    case 1 -> login();
                    case 2 -> register();
                    case 555 -> adminLogin();
                    case 3 -> System.out.println("Exiting...");
                    default -> System.err.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.err.println("Invalid choice!");
            }
        } while (choice != 3);

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login() {
        try {
            System.out.print("Enter username: ");
            String username = br.readLine();
            System.out.print("Enter password: ");
            String password = br.readLine();

            out.println("borrower");
            out.println(username);
            out.println(password);

            String response = in.readLine();
            if (response.equals("Authenticated")) {
                Borrower borrower = new Borrower(username, password);
                BorrowerView borrowerView = new BorrowerView(borrower, in, out);
                borrowerView.operations();
            } else {
                System.err.println("Authentication failed!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void register() {
        try {
            System.out.print("Enter username: ");
            String username = br.readLine();
            System.out.print("Enter password: ");
            String password = br.readLine();
            System.out.print("Enter name: ");
            String name = br.readLine();

            out.println("register");
            out.println(username);
            out.println(password);
            out.println(name);

            String response = in.readLine();
            if (response.equals("Registration Successful")) {
                System.out.println("Registration Successful! Please login.");
            } else {
                System.err.println("Registration failed!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void adminLogin() {
        try {
            System.out.print("Enter admin username: ");
            String username = br.readLine();
            System.out.print("Enter admin password: ");
            String password = br.readLine();

            out.println("admin");
            out.println(username);
            out.println(password);

            String response = in.readLine();
            if (response.equals("Authenticated")) {
                AdminView adminView = new AdminView(in, out);
                adminView.operations();
            } else {
                System.err.println("Authentication failed!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            LMS lms = new LMS("localhost", 12345);
            lms.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
