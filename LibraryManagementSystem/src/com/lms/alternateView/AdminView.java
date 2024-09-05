package com.lms.alternateView;

import com.lms.models.Book;
import com.lms.services.AdminService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;
import java.util.List;

public class AdminView {
    private AdminService adminService;
    private BufferedReader in;
    private PrintWriter out;

    public AdminView(PrintWriter out, BufferedReader in) {
        this.in = in;
        this.out = out;
        this.adminService = new AdminService();
    }



    public void operations() throws SocketException {
        try {
            int choice = -1;
            do {
                choice = Integer.parseInt(in.readLine());

                switch (choice) {
                    case 1 -> addBook();
                    case 2 -> removeBook();
                    case 3 -> searchBook();
                    case 4 -> viewBooks();
                    case 0 -> out.println("\nExiting...");
                    default -> out.println("\nInvalid Operation");
                }
            } while (choice != 0);
        }catch (SocketException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addBook() throws IOException {
        try {
            String isbn = in.readLine();

            String name = in.readLine();

            String author = in.readLine();

            String genre = in.readLine();

            Book book = new Book(isbn, name, author, genre);
            adminService.addBook(book);
            out.println("Book added Successfully!");
        } catch (Exception e) {
            out.println(e.getMessage());
        }

    }

    private void removeBook() throws IOException {
        try {
            String isbn = in.readLine();
            adminService.removeBook(isbn);
            out.println("Book removed Successfully!");
        } catch (Exception e) {
            out.println(e.getMessage());
        }

    }

    private void searchBook() throws IOException {

        int searchChoice = Integer.parseInt(in.readLine());

        switch (searchChoice) {
            case 1 -> handleSearchBookByName();
            case 2 -> handleSearchBookByAuthor();
            case 3 -> handleSearchBookByISBN();
            case 4 -> handleSearchBookByGenre();
            default -> out.println("Invalid search option");
        }
    }

    private void handleSearchBookByName() throws IOException {
        String name = in.readLine();
        List<Book> books = adminService.searchBooksByName(name);
        sendBooks(books);
    }

    private void handleSearchBookByAuthor() throws IOException {
        String author = in.readLine();
        List<Book> books = adminService.searchBooksByAuthor(author);
        sendBooks(books);
    }

    private void handleSearchBookByISBN() throws IOException {
        String isbn = in.readLine();
        Book book = adminService.getBookByIsbn(isbn);
        if (book != null) {
            out.println(book);
        } else {
            out.println("Book not found with ISBN: " + isbn);
        }
        out.println("end");
    }

    private void handleSearchBookByGenre() throws IOException {
        String genre = in.readLine();
        List<Book> books = adminService.getBooksByGenre(genre);
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
        List<Book> books = adminService.getAllBooks();
        if (books.isEmpty()) {
            out.println("No books available.");
        } else {
            for (Book book : books) {
                out.println(book);
            }
        }
        out.println("end");
    }
}
