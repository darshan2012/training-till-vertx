package com.lms.views;

import com.lms.server.db.BooksDB;
import com.lms.server.models.Book;
import com.lms.server.services.AdminService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

public class AdminView {
    private AdminService adminService;
    private BufferedReader inputReader;

    public AdminView(BufferedReader inputReader) {
        this.inputReader = inputReader;
    }

    public AdminView() {
        inputReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public AdminView(BufferedReader in, PrintWriter out) {

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
                choice = Integer.parseInt(inputReader.readLine());
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
            String ISBN = inputReader.readLine();
            System.out.print("Enter Book Name: ");
            String name = inputReader.readLine();
            System.out.print("Enter Author Name: ");
            String author = inputReader.readLine();
            System.out.print("Enter Genre: ");
            String genre = inputReader.readLine();

            Book book = new Book(ISBN, name, author, genre);
            AdminService.addBook(book);
            System.out.println("\n\tBook added Successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeBook() {
        try {
            System.out.print("Enter ISBN: ");
            String ISBN = inputReader.readLine();
            AdminService.removeBook(ISBN);
            System.out.println("\n\tBook removed Successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void searchOptions(){
        System.out.println("\n1.Search by name\n2.Search by author name\n3.Search by ISBN\n4.Search by genre\n0.Exit");
        System.out.print("Enter your choice: ");
    }
    private void searchBook() {
        int choice = -1;
        try {
            do {
                searchOptions();
                choice = Integer.parseInt(inputReader.readLine());
                switch (choice) {
                    case 1 -> searchBookByName();
                    case 2 -> searchBookByAuthor();
                    case 3 -> searchBookByISBN();
                    case 4 -> searchBookByGenre();
                    case 0 -> System.out.println("\n\tExiting search...");
                    default -> System.err.println("\n\tInvalid Operation");
                }
            } while (choice != 0);
        } catch (Exception e) {
            System.out.println("Something went wrong..." + e.getMessage());
        }
    }
    private <T> void printBooks(List<T> books){
        for(T book : books)
            System.out.println(book);

    }
    private void searchBookByName() throws IOException {
        System.out.print("Enter book name: ");
        String name = inputReader.readLine();
        List<Book> books = adminService.searchBooksByName(name);
        printBooks(books);
    }

    private void searchBookByAuthor() throws IOException {
        System.out.print("Enter author name: ");
        String author = inputReader.readLine();
        List<Book> books = adminService.searchBooksByAuthor(author);
        printBooks(books);
    }

    private void searchBookByISBN() throws IOException {
        System.out.print("Enter ISBN: ");
        String ISBN = inputReader.readLine();
        Book book = adminService.getBookByISBN(ISBN);
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("Book not found with ISBN: " + ISBN);
        }
    }

    private void searchBookByGenre() throws IOException {
        System.out.print("Enter genre: ");
        String genre = inputReader.readLine();
        List<Book> books = adminService.getBooksByGenre(genre);
        printBooks(books);
    }

    private void viewBooks() {
        printBooks(BooksDB.getAllBooks());
    }
}
