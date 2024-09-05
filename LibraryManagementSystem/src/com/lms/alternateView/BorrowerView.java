package com.lms.alternateView;

import com.lms.models.Book;
import com.lms.models.BorrowedBook;
import com.lms.models.Borrower;
import com.lms.services.BorrowerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;
import java.util.List;

public class BorrowerView {
    private BorrowerService borrowerService;
    private BufferedReader in;
    private PrintWriter out;
    private Borrower borrower;

    public BorrowerView(Borrower borrower, PrintWriter out, BufferedReader in) {
        this.borrower = borrower;
        this.in = in;
        this.out = out;
        this.borrowerService = new BorrowerService(borrower);
    }

    private void choices() {
        out.println("1. Issue Book");
        out.println("2. Return Book");
        out.println("3. Search Books");
        out.println("4. View Available Books");
        out.println("5. View Borrowed Books");
        out.println("0. Exit");
        out.print("Please choose an Operation: ");
    }

    public void operations() throws SocketException {
        try {
            int choice = -1;
            do {
//                choices();
                choice = Integer.parseInt(in.readLine());
                switch (choice) {
                    case 1 -> issueBook();
                    case 2 -> returnBook();
                    case 3 -> searchBook();
                    case 4 -> viewBooks();
                    case 5 -> getBorrowedBooks();
                }
            } while (choice != 0);
        } catch (SocketException e) {
            throw e;
        } catch (Exception e) {
//            e.printStackTrace();
            out.println("An error occurred: " + e.getMessage());
        }
    }

    private void issueBook() throws IOException {
        String isbn = in.readLine();
        try {
            if (borrowerService.issueBook(isbn)) {
                out.println("Book Issued Successfully!");
            } else {
                out.println("Could not issue Book. It might be unavailable or already borrowed.");
            }
        } catch (Exception e) {
            out.println("Error issuing book: " + e.getMessage());
        }
    }

    private void returnBook() throws IOException {

        String isbn = in.readLine();
        try {
            long penalty = borrowerService.returnBook(isbn);
            out.println("Book returned Successfully!");
            if (penalty > 0) {
                out.println("You have a penalty of: " + penalty + " for late return.");
            }
        } catch (Exception e) {
            out.println("Error returning book: " + e.getMessage());
        }
    }

    private void searchBook() throws IOException {

        int searchChoice = Integer.parseInt(in.readLine());

        switch (searchChoice) {
            case 1 -> handleSearchBookByName();
            case 2 -> handleSearchBookByAuthor();
            case 3 -> handleSearchBookByISBN();
            case 4 -> handleSearchBookByGenre();
//            default -> out.println("Invalid search option");
        }
    }

    private void handleSearchBookByName() throws IOException {
        String name = in.readLine();
        List<Book> books = borrowerService.searchBooksByName(name);
        sendBooks(books);
    }

    private void handleSearchBookByAuthor() throws IOException {
        String author = in.readLine();
        List<Book> books = borrowerService.searchBooksByAuthor(author);
        sendBooks(books);
    }

    private void handleSearchBookByISBN() throws IOException {
        String isbn = in.readLine();
        Book book = borrowerService.getBookByIsbn(isbn);
        if (book != null) {
            out.println(book);
        } else {
            out.println("Book not found with ISBN: " + isbn);
        }
        out.println("end");
    }

    private void handleSearchBookByGenre() throws IOException {
        String genre = in.readLine();
        List<Book> books = borrowerService.getBooksByGenre(genre);
        sendBooks(books);
    }

    private void sendBooks(List<Book> books) {
        if (books != null && !books.isEmpty()) {
            for (Book book : books) {
                out.println(book);
            }
        } else {
            out.println("No books found.");
        }
        out.println("end");
    }

    private void viewBooks() {
        List<Book> books = borrowerService.getAllBooks();
       sendBooks(books);
    }

    private void getBorrowedBooks() {
        List<BorrowedBook> borrowedBooks = borrowerService.getBorrowedBooks();
        if (borrowedBooks.isEmpty()) {
            out.println("You have not borrowed any books.");
        } else {
            for (BorrowedBook book : borrowedBooks) {
                out.println(book);
            }
        }
        out.println("end");
    }
}
