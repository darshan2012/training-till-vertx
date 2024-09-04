package com.lms.services;

import com.lms.db.BooksDB;
import com.lms.db.BorrowedBooksDB;
import com.lms.db.BorrowersDB;
import com.lms.exceptions.BookNotFoundException;
import com.lms.exceptions.BookUnavailableException;
import com.lms.models.Book;
import com.lms.models.BorrowedBook;
import com.lms.models.Borrower;

import java.util.List;

public class BorrowerService extends LibUserService {
    public static int MAX_BORROW_LIMIT = 5;
    public static int MAX_HOLD_DAYS = 14;
    public static long PENALTY_PER_DAY = 10;
    private Borrower borrower;

    public BorrowerService(Borrower borrower) {
        this.borrower = borrower;
    }

    public  boolean issueBook(String isbn){
        if(!BooksDB.checkIfIsbnExist(isbn))
        {
            throw new BookNotFoundException();
        }
        Book book = BooksDB.getBookByIsbn(isbn);
        if (!book.getIsAvailable()) {
            throw new BookUnavailableException();
        }
        book.setIsAvailable(false);
        Borrower borrower = BorrowersDB.getBorrowerByusername(this.borrower.getUsername());
        BorrowedBook borrowedBook = new BorrowedBook(book);
        borrower.addBookToBorrowedBook(borrowedBook);
        return true;
    }

    public long returnBook(String isbn){
        if(!BooksDB.checkIfIsbnExist(isbn))
        {
            throw new BookNotFoundException();
        }
        Book book = BooksDB.getBookByIsbn(isbn);
        if (book.getIsAvailable()) {
            throw new BookUnavailableException("Book is Already Available");
        }
        borrower.removeBookFromBorrow(isbn);

        return calculatePanelty();
    }

    public long calculatePanelty(){

        return 0;
    }

    public List<BorrowedBook> getBorrowedBooks(){
        return borrower.getBorrowedBooks();
    }
}
