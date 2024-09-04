package com.lms.alternateView;


import com.lms.models.Book;
import com.lms.services.AdminService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AdminView {
    private AdminService adminService;
    private BufferedReader in;
    private PrintWriter out;

    public AdminView(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
        this.adminService = new AdminService();
    }

    private void choices() {
        out.println("1. Add Book\n2. Remove Book\n3. Search Books\n4. View Books\n0. Exit");
        out.print("Please choose an Operation: ");
    }

    public void operations() {
        try {
            int choice = -1;
            do {
                choices();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addBook() throws IOException {
        out.print("Enter ISBN: ");
        String isbn = in.readLine();
        out.print("Enter Book Name: ");
        String name = in.readLine();
        out.print("Enter Author Name: ");
        String author = in.readLine();
        out.print("Enter Genre: ");
        String genre = in.readLine();

        Book book = new Book(isbn, name, author, genre);
        adminService.addBook(book);
        out.println("\nBook added Successfully!");
    }

    private void removeBook() throws IOException {
        out.print("Enter ISBN: ");
        String isbn = in.readLine();
        adminService.removeBook(isbn);
        out.println("\nBook removed Successfully!");
    }

    private void searchBook() throws IOException {
        // Implement search options similar to addBook and removeBook methods
    }

    private void viewBooks() {
        List<Book> books = adminService.getAllBooks();
        for (Book book : books) {
            out.println(book);
        }
    }
}
