package com.lms.alternateView;

import com.lms.models.Book;
import com.lms.models.BorrowedBook;
import com.lms.models.Borrower;
import com.lms.services.BorrowerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class BorrowerView {
    private BorrowerService borrowerService;
    private BufferedReader in;
    private PrintWriter out;
    private Borrower borrower;

    public BorrowerView(Borrower borrower, BufferedReader in, PrintWriter out) {
        this.borrower = borrower;
        this.in = in;
        this.out = out;
        this.borrowerService = new BorrowerService(borrower);
    }

    private void choices() {
        out.println("1. Issue Book\n2. Return Book\n3. Search Books\n4. View Books\n5. View Borrowed Books\n0. Exit");
        out.print("Please choose an Operation: ");
    }

    public void operations() {
        try {
            int choice = -1;
            do {
                choices();
                choice = Integer.parseInt(in.readLine());
                switch (choice) {
                    case 1 -> issueBook();
                    case 2 -> returnBook();
                    case 3 -> searchBook();
                    case 4 -> viewBooks();
                    case 5 -> getBorrowedBooks();
                    case 0 -> out.println("\nExiting...");
                    default -> out.println("\nInvalid Operation");
                }
            } while (choice != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void issueBook() throws IOException {
        out.print("Enter ISBN: ");
        String isbn = in.readLine();
        try {
            if (borrowerService.issueBook(isbn)) {
                out.println("\nBook Issued Successfully!");
            } else {
                out.println("Could not issue Book");
            }
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }

    private void returnBook() throws IOException {
        out.print("Enter ISBN: ");
        String isbn = in.readLine();
        try {
            long penalty = borrowerService.returnBook(isbn);
            out.println("\nBook returned Successfully!");
            if (penalty > 0) {
                out.println("Your penalty is " + penalty);
            }
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }

    private void searchBook() throws IOException {
        // Implement search options similar to issueBook and returnBook methods
    }

    private void viewBooks() {
        List<Book> books = borrowerService.getAllBooks();
        for (Book book : books) {
            out.println(book);
        }
    }

    private void getBorrowedBooks() {
        List<BorrowedBook> books = borrowerService.getBorrowedBooks();
        for (var book : books) {
            out.println(book);
        }
    }
}

