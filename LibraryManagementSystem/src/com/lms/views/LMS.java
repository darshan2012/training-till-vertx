package com.lms.views;

import com.lms.server.models.Borrower;
import com.lms.server.services.AuthenticationService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LMS {
    BufferedReader inputReader;
    private static LMS lms;

    private LMS(){
        inputReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static synchronized LMS getLMS(){
        if (lms == null) {
            lms = new LMS();
        }
        return lms;
    }

    private void authChoices(){
        System.out.println("\n1.Login\n2.Registration\n3.Exit\n");
    }

    private void displayHomeHeader() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("+-------------------------------------------+");
        System.out.println("|                                           |");
        System.out.println("| ======> LiinputReaderary Management System <====== |");
        System.out.println("|                                           |");
        System.out.println("+-------------------------------------------+");
        System.out.println();
    }

    public void start(){
        displayHomeHeader();
        int choice=0;
        do{
            try{
                authChoices();
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(inputReader.readLine());
                switch (choice){
                    case 1 -> login();
                    case 2 -> register();
                    case 555-> adminLogin();
                    case 3 -> System.out.println("Exiting...");
                    default -> System.err.println("Invalid choice!");
                }
            }
            catch (Exception e){
                System.err.println("Invalid choice!");
            }
        }while(choice != 3);
    }

    private void adminLogin() {
        new AdminView().operations();
    }

    private void register() {
        try {
            System.out.println("\n\t\t*Register");
            System.out.print("\nEnter username: ");
            String username = inputReader.readLine();
            System.out.print("Enter password: ");
            String password = inputReader.readLine();
            System.out.print("Enter name: ");
            String name = inputReader.readLine();
            boolean success = AuthenticationService.registerUser(username, password, name);
            if(success)
            {
                System.out.println("\n\n\tRegistration Successful!");
                login();
            }
        }catch (Exception e){
            System.err.println("\nCould not Register: " + e.getMessage());
        }
    }

    private void login() {
        try {
            System.out.println("\n\t\t*Login\n");
            System.out.print("\nEnter username: ");
            String username = inputReader.readLine();
            System.out.print("Enter password: ");
            String password = inputReader.readLine();
            Borrower user = AuthenticationService.signIn(username,password);
            if(user != null)
            {
                System.out.println("\n\t\tLogin successful!");
            }
            new BorrowerView(user).operations();
        }catch (Exception e){
            System.err.println("\nCould not login: " + e.getMessage());
        }
    }
}
