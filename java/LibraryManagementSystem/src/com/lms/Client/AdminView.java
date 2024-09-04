package com.lms.Client;


import com.lms.models.Book;
import com.lms.services.AdminService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

public class AdminView {
    private PrintWriter out;
    private BufferedReader in;
    private BufferedReader br;

    public AdminView(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    private void choices() {
        System.out.println("1. Add Book\n2. Remove Book\n3. Search Books\n4. View Books\n0. Exit");
        System.out.print("Please choose an Operation: ");
    }

    public void operations() {
        try {
            int choice = -1;
            do {
                choices();
                choice = Integer.parseInt(br.readLine());
                switch (choice) {
                    case 1 -> addBook();
                    case 2 -> removeBook();
                    case 3 -> searchBook();
                    case 4 -> viewBooks();
                    case 0 -> System.out.println("\nExiting...");
                    default -> System.err.println("\nInvalid Operation");
                }
            } while (choice != 0);
        } catch (Exception e) {
            System.err.println("Something went wrong...");
        }
    }

    private void addBook() {
        try {
            System.out.print("Enter ISBN: ");
            String isbn = br.readLine();
            System.out.print("Enter Book Name: ");
            String name = br.readLine();
            System.out.print("Enter Author Name: ");
            String author = br.readLine();
            System.out.print("Enter Genre: ");
            String genre = br.readLine();

            out.println("addBook");
            out.println(isbn);
            out.println(name);
            out.println(author);
            out.println(genre);

            String response = in.readLine();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeBook() {
        try {
            System.out.print("Enter ISBN: ");
            String isbn = br.readLine();

            out.println("removeBook");
            out.println(isbn);

            String response = in.readLine();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchBook() {
        try {
            System.out.println("\n1. Search by Name\n2. Search by Author\n3. Search by ISBN\n4. Search by Genre\n0. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(br.readLine());

            switch (choice) {
                case 1 -> searchByName();
                case 2 -> searchByAuthor();
                case 3 -> searchByISBN();
                case 4 -> searchByGenre();
                case 0 -> System.out.println("\nExiting search...");
                default -> System.err.println("\nInvalid Operation");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchByName() throws IOException {
        System.out.print("Enter book name: ");
        String name = br.readLine();

        out.println("searchByName");
        out.println(name);

        String response = in.readLine();
        System.out.println(response);
    }

    private void searchByAuthor() throws IOException {
        System.out.print("Enter author name: ");
        String author = br.readLine();

        out.println("searchByAuthor");
        out.println(author);

        String response = in.readLine();
        System.out.println(response);
    }

    private void searchByISBN() throws IOException {
        System.out.print("Enter ISBN: ");
        String isbn = br.readLine();

        out.println("searchByISBN");
        out.println(isbn);

        String response = in.readLine();
        System.out.println(response);
    }

    private void searchByGenre() throws IOException {
        System.out.print("Enter genre: ");
        String genre = br.readLine();

        out.println("searchByGenre");
        out.println(genre);

        String response = in.readLine();
        System.out.println(response);
    }

    private void viewBooks() {
        out.println("viewBooks");

        try {
            String response;
            while (!(response = in.readLine()).equals("END")) {
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

