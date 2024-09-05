package com.lms.views;

import com.lms.server.exceptions.BookNotFoundException;
import com.lms.server.exceptions.BookUnavailableException;
import com.lms.server.models.Book;
import com.lms.server.models.BorrowedBook;
import com.lms.server.models.Borrower;
import com.lms.server.services.BorrowerService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

public class BorrowerView {
    BorrowerService borrowerService;
    BufferedReader inputReader;
    Borrower borrower;
    public BorrowerView(BufferedReader inputReader){
        this.inputReader = inputReader;
    }

    public BorrowerView(Borrower borrower){
        inputReader = new BufferedReader(new InputStreamReader(System.in));
        borrowerService = new BorrowerService(borrower);
    }

    public BorrowerView(Borrower borrower, BufferedReader in, PrintWriter out) {
    }

    private void choices(){
        System.out.println("1.Issue Book\n2.Return Book\n3.Search Books\n4.View Books\n0.Exit");
        System.out.print("Please choose an Operation: ");
    }

    public void operations() {
        try {
            int choice=-1;
            do{
                choices();
                choice = Integer.parseInt(inputReader.readLine());
                switch (choice){
                    case 1 -> issueBook();
                    case 2 -> returnBook();
                    case 3 -> searchBook();
                    case 4 -> viewBook();
                    case 5 -> getBorrowedBooks();
                    case 0 -> System.out.println("\n\tExiting...");
                    default -> System.err.println("\n\tInvalid Operation");
                }
            }while(choice != 0);

        }catch (Exception e)
        {
            System.err.println("Something went wrong...");
        }
    }

    private void getBorrowedBooks() {
        List<BorrowedBook> borrowedBooks =  borrowerService.getBorrowedBooks();
        printBooks(borrowedBooks);
    }

    private void viewBook() {
        List<Book> books = borrowerService.getAllBooks();
        for(var book : books)
            System.out.println(book);

    }
    private <T> void printBooks(List<T> books){
        for(T book : books)
            System.out.println(book);

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

    private void searchBookByName() throws IOException {
        System.out.print("Enter book name: ");
        String name = inputReader.readLine();
        List<Book> books = borrowerService.searchBooksByName(name);
        printBooks(books);
    }

    private void searchBookByAuthor() throws IOException {
        System.out.print("Enter author name: ");
        String author = inputReader.readLine();
        List<Book> books = borrowerService.searchBooksByAuthor(author);
        printBooks(books);
    }

    private void searchBookByISBN() throws IOException {
        System.out.print("Enter ISBN: ");
        String ISBN = inputReader.readLine();
        Book book = borrowerService.getBookByISBN(ISBN);
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("Book not found with ISBN: " + ISBN);
        }
    }

    private void searchBookByGenre() throws IOException {
        System.out.print("Enter genre: ");
        String genre = inputReader.readLine();
        List<Book> books = borrowerService.getBooksByGenre(genre);
        printBooks(books);
    }

    private void returnBook() {
        System.out.println("Enter ISBN: ");
        try {
            String ISBN = inputReader.readLine();
            long panelty = borrowerService.returnBook(ISBN);
            System.out.println("\n\t\tBook returned Successfully!!  ");
            if (panelty > 0)
                System.out.println("your panelty is " + panelty);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (BookUnavailableException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Something went wrong..." + e.getMessage());
        }
    }

    private void issueBook() {
        System.out.println("Enter ISBN: ");
        try {
            String ISBN = inputReader.readLine();
            if (borrowerService.issueBook(ISBN)) {
                System.out.println("\n\t\tBook Issued Successfully!!  \n");
            } else
                System.out.println("Could not issue Book");
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (BookUnavailableException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Something went wrong..." + e.getMessage());
        }
    }


}
