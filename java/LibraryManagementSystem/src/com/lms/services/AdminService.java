package com.lms.services;

import com.lms.db.BooksDB;
import com.lms.db.BorrowedBooksDB;
import com.lms.exceptions.BookNotFoundException;
import com.lms.exceptions.BookUnavailableException;
import com.lms.models.Book;

import java.util.List;

public class AdminService extends LibUserService {
    public static void addBook(Book book) {
        if (!BooksDB.checkIfIsbnExist(book.getIsbn())) {
            BooksDB.addBook(book.getIsbn(), book.getName(), book.getAuthor(), book.getGenre());
        } else {
            throw new RuntimeException("Book Already Exist!");
        }
    }

    public static void removeBook(String isbn) {
        if (BooksDB.checkIfIsbnExist(isbn)) {
            if (!BooksDB.getBookByIsbn(isbn).getIsAvailable()) {
                throw new BookUnavailableException();
            }
            BooksDB.removeBook(isbn);
        }  else {
            throw new BookNotFoundException();
        }
    }


}
