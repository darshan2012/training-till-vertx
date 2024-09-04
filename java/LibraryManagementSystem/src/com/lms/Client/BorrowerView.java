package com.lms.Client;

import com.lms.models.Borrower;
import com.lms.services.BorrowerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class BorrowerView {
    private BorrowerService borrowerService;
    private BufferedReader in;
    private PrintWriter out;
    private BufferedReader br;
    private Borrower borrower;

    public BorrowerView(Borrower borrower, PrintWriter out, BufferedReader in, BufferedReader br) {
        this.borrower = borrower;
        this.in = in;
        this.out = out;
        this.br = br;
        this.borrowerService = new BorrowerService(borrower);
    }

    private void choices() {
        System.out.println("1. Issue Book");
        System.out.println("2. Return Book");
        System.out.println("3. Search Books");
        System.out.println("4. View Available Books");
        System.out.println("5. View Borrowed Books");
        System.out.println("0. Exit");
        System.out.print("Please choose an Operation: ");
    }

    public void operations() {
        try {
            int choice;
            do {
                choices();
                choice = Integer.parseInt(br.readLine());
                out.println(choice);  // Send the choice to the server
                switch (choice) {
                    case 1 -> issueBook();
                    case 2 -> returnBook();
                    case 3 -> searchBook();
                    case 4 -> viewBooks();
                    case 5 -> getBorrowedBooks();
                    case 0 -> out.println("\nExiting...");
                    default -> System.out.println("\nInvalid Operation");
                }
            } while (choice != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void issueBook() throws IOException {
        System.out.print("Enter ISBN: ");
        String isbn = br.readLine();
        out.println(isbn);
        String response = in.readLine();
        System.out.println(response);

    }

    private void returnBook() throws IOException {
        System.out.print("Enter ISBN: ");
        String isbn = br.readLine();
        out.println(isbn);
        String response = in.readLine();
        System.out.println(response);

    }
    private void searchOptions() {
        System.out.println("\n1. Search by name");
        System.out.println("2. Search by author name");
        System.out.println("3. Search by ISBN");
        System.out.println("4. Search by genre");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private void searchBook() {
        int choice = -1;
        try {
                searchOptions();
                choice = Integer.parseInt(br.readLine());
                out.println(choice);
                switch (choice) {
                    case 1 -> searchBookByName();
                    case 2 -> searchBookByAuthor();
                    case 3 -> searchBookByISBN();
                    case 4 -> searchBookByGenre();
                    case 0 -> System.out.println("\nExiting search...");
                    default -> System.err.println("\nInvalid Operation");
                }
        } catch (Exception e) {
            System.out.println("Something went wrong..." + e.getMessage());
        }
    }

    private void searchBookByName() throws IOException {
        System.out.print("Enter book name: ");
        String name = br.readLine();
        out.println(name);
        displayBooks();
    }

    private void searchBookByAuthor() throws IOException {
        System.out.print("Enter author name: ");
        String author = br.readLine();
        out.println(author);
        displayBooks();
    }

    private void searchBookByISBN() throws IOException {
        System.out.print("Enter ISBN: ");
        String isbn = br.readLine();
        out.println(isbn);
        String book = in.readLine();
        if (!book.equals("end")) {
            System.out.println(book);
        } else {
            System.out.println("Book not found with ISBN: " + isbn);
        }
    }

    private void searchBookByGenre() throws IOException {
        System.out.print("Enter genre: ");
        String genre = br.readLine();
        out.println(genre);
        displayBooks();
    }

    private void displayBooks() throws IOException {
        String book;
        while (!(book = in.readLine()).equals("end")) {
            System.out.println(book);
        }
    }

    private void viewBooks() throws IOException {
        String book;
        while (!(book = in.readLine()).equals("end")) {
            System.out.println(book);
        }

    }

    private void getBorrowedBooks() throws IOException {
        String book;
        while (!(book = in.readLine()).equals("end")) {
            System.out.println(book);
        }

    }
}
