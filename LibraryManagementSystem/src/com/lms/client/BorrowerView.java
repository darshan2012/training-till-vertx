package com.lms.client;

import com.lms.server.models.Borrower;
import com.lms.server.services.BorrowerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BorrowerView {
    private BorrowerService borrowerService;
    private BufferedReader inStream;
    private PrintWriter outStream;
    private BufferedReader inputReader;
    private Borrower borrower;

    public BorrowerView(Borrower borrower, PrintWriter outStream, BufferedReader inStream, BufferedReader inputReader) {
        this.borrower = borrower;
        this.inStream = inStream;
        this.outStream = outStream;
        this.inputReader = inputReader;
        this.borrowerService = new BorrowerService(borrower);
    }

    private void choices() {
        System.out.println("1. Issue Book");
        System.out.println("2. Return Book");
        System.out.println("3. Search Books");
        System.out.println("4. View All Books");
        System.out.println("5. View Borrowed Books");
        System.out.println("0. Exit");
        System.out.print("Please choose an Operation: ");
    }

    public void operations() {
        try {
            int choice;
            do {
                choices();
                choice = Integer.parseInt(inputReader.readLine());
                outStream.println(choice);
                switch (choice) {
                    case 1 -> issueBook();
                    case 2 -> returnBook();
                    case 3 -> searchBook();
                    case 4 -> viewBooks();
                    case 5 -> getBorrowedBooks();
                    case 0 -> System.out.println("\n\tExiting...");
                    default -> System.out.println("\nInvalid Operation");
                }
            } while (choice != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void issueBook() throws IOException {
        System.out.print("Enter ISBN: ");
        String ISBN = inputReader.readLine();
        outStream.println(ISBN);
        String response = inStream.readLine();
        System.out.println(response);

    }

    private void returnBook() throws IOException {
        System.out.print("Enter ISBN: ");
        String ISBN = inputReader.readLine();
        outStream.println(ISBN);
        String response = inStream.readLine();
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
                choice = Integer.parseInt(inputReader.readLine());
                outStream.println(choice);
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
        String name = inputReader.readLine();
        outStream.println(name);
        displayBooks();
    }

    private void searchBookByAuthor() throws IOException {
        System.out.print("Enter author name: ");
        String author = inputReader.readLine();
        outStream.println(author);
        displayBooks();
    }

    private void searchBookByISBN() throws IOException {
        System.out.print("Enter ISBN: ");
        String ISBN = inputReader.readLine();
        outStream.println(ISBN);
        String book = inStream.readLine();
        if (!book.equals("end")) {
            System.out.println(book);
        } else {
            System.out.println("Book not found with ISBN: " + ISBN);
        }
    }

    private void searchBookByGenre() throws IOException {
        System.out.print("Enter genre: ");
        String genre = inputReader.readLine();
        outStream.println(genre);
        displayBooks();
    }

    private void displayBooks() throws IOException {
        String book;
        while (!(book = inStream.readLine()).equals("end")) {
            System.out.println(book);
        }
    }

    private void viewBooks() throws IOException {
        String book;
        while (!(book = inStream.readLine()).equals("end")) {
            System.out.println(book);
        }

    }

    private void getBorrowedBooks() throws IOException {
        String book;
        while (!(book = inStream.readLine()).equals("end")) {
            System.out.println(book);
        }

    }
}
