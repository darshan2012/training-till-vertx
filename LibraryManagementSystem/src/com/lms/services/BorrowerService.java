package com.lms.services;

import com.lms.db.BooksDB;
import com.lms.db.BorrowedBooksDB;
import com.lms.db.BorrowersDB;
import com.lms.exceptions.BookNotFoundException;
import com.lms.exceptions.BookUnavailableException;
import com.lms.models.Book;
import com.lms.models.BorrowedBook;
import com.lms.models.Borrower;
import com.lms.util.PenaltyCalculator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class BorrowerService extends LibUserService {
    public static int MAX_BORROW_LIMIT = 5;
    public static int MAX_HOLD_DAYS = 14;
    public static long PENALTY_PER_DAY = 10;
    public static PenaltyCalculator penaltyCalculator;
    static {
        penaltyCalculator = (issueDate, dueDate) -> {
            long penalty = (Duration.between(issueDate,dueDate).toDays() - MAX_HOLD_DAYS) * PENALTY_PER_DAY;
            return penalty > 0 ? penalty : 0;
        };
    }


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
        BorrowedBooksDB.addBorrowedBook(borrowedBook);
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
        book.setIsAvailable(true);
        BorrowedBook borrowedBook = BorrowedBooksDB.getBorrowedBook(isbn);
        long penalty = penaltyCalculator.calculatePenalty(borrowedBook.getIssueDate(), LocalDateTime.now());
        borrower.removeBookFromBorrow(isbn);
        BorrowedBooksDB.removeBorrowedBook(isbn);
        return penalty;
    }

    public List<BorrowedBook> getBorrowedBooks(){
        return borrower.getBorrowedBooks();
    }
}
