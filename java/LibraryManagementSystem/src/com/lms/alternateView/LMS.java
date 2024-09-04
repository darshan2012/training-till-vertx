package com.lms.alternateView;

import com.lms.models.Borrower;
import com.lms.services.AuthenticationService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.SocketException;

public class LMS {
    BufferedReader br;
    private static LMS lms;
    private PrintWriter out;
    private BufferedReader in;

    public LMS(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
        br = new BufferedReader(new InputStreamReader(System.in));
    }


    private void authChoices(){
        System.out.println("\n1.Login\n2.Registration\n3.Exit\n");
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

    public void start(){

        displayHomeHeader();
        int choice=0;

            try{
//                authChoices();
//                System.out.print("Enter your choice: ");
                do{
                choice = Integer.parseInt(in.readLine());

                switch (choice){
                    case 1 -> login();
                    case 2 -> register();
                    case 555-> adminLogin();
                    case 3 -> System.out.println("Exiting...");
                    default -> System.err.println("Invalid choice!");
                }
                }while(choice != 3);
            }
            catch (SocketException e)
            {
                System.out.println("client Exited...");
            }
            catch (Exception e){
//                e.printStackTrace();
                System.err.println(e.getMessage());
                out.println("Invalid choice!");
            }

    }

    private void adminLogin() throws SocketException {
        new AdminView(out,in).operations();
    }

    private void register() {
        try {
            String username = in.readLine();
            String password = in.readLine();
            String name = in.readLine();
            boolean success = AuthenticationService.registerUser(username, password, name);
            if(success) {
                out.println("Registered");
            } else {
                out.println("Registration Failed");
            }
        } catch (Exception e) {
            System.err.println("\nCould not Register: " + e.getMessage());
            out.println("Error: " + e.getMessage());
        }
    }


    private void login() {
        try {

            String username = in.readLine();
            String password = in.readLine();
            Borrower user = AuthenticationService.signIn(username,password);
            if(user != null)
            {
//                System.out.println("\n\t\tLogin successful!");
                out.println("Authenticated");
            }
            new BorrowerView(user,out,in).operations();
        }catch (Exception e){
            System.err.println("\nCould not login: " + e.getMessage());
            out.println(e.getMessage());
        }
    }
}
