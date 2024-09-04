package com.lms.views;

import com.lms.db.BorrowedBooksDB;
import com.lms.exceptions.BookNotFoundException;
import com.lms.exceptions.BookUnavailableException;
import com.lms.models.Book;
import com.lms.models.BorrowedBook;
import com.lms.models.Borrower;
import com.lms.services.BorrowerService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

public class BorrowerView {
    BorrowerService borrowerService;
    BufferedReader br;
    Borrower borrower;
    public BorrowerView(BufferedReader br){
        this.br = br;
    }

    public BorrowerView(Borrower borrower){
        br = new BufferedReader(new InputStreamReader(System.in));
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
                choice = Integer.parseInt(br.readLine());
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
        System.out.println("\n1.Search by name\n2.Search by author name\n3.Search by isbn\n4.Search by genre\n0.Exit");
        System.out.print("Enter your choice: ");
    }
    private void searchBook() {
        int choice = -1;
        try {
            do {
                searchOptions();
                choice = Integer.parseInt(br.readLine());
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
        String name = br.readLine();
        List<Book> books = borrowerService.searchBooksByName(name);
        printBooks(books);
    }

    private void searchBookByAuthor() throws IOException {
        System.out.print("Enter author name: ");
        String author = br.readLine();
        List<Book> books = borrowerService.searchBooksByAuthor(author);
        printBooks(books);
    }

    private void searchBookByISBN() throws IOException {
        System.out.print("Enter ISBN: ");
        String isbn = br.readLine();
        Book book = borrowerService.getBookByIsbn(isbn);
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("Book not found with ISBN: " + isbn);
        }
    }

    private void searchBookByGenre() throws IOException {
        System.out.print("Enter genre: ");
        String genre = br.readLine();
        List<Book> books = borrowerService.getBooksByGenre(genre);
        printBooks(books);
    }
//        printBooks(borrowerService.searchBooksByauthor("Harper Lee"));
//        printBooks(borrowerService.searchBooksByName(""));


    private void returnBook() {
        System.out.println("Enter isbn: ");
        try {
            String isbn = br.readLine();
            long panelty = borrowerService.returnBook(isbn);
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
        System.out.println("Enter isbn: ");
        try {
            String isbn = br.readLine();
            if (borrowerService.issueBook(isbn)) {
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
