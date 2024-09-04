package com.lms.db;

import com.lms.models.BorrowedBook;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BorrowedBooksDB {
    private static Map<String, BorrowedBook> borrowedBookMap = new ConcurrentHashMap<>();
    public boolean checkIfIsbnExist(String isbn){
        return borrowedBookMap.containsKey(isbn);
    }
    public void removeBook(String isbn){
        borrowedBookMap.remove(isbn);
    }
    public void addBorrowedBook(BorrowedBook borrowedBook){
        borrowedBookMap.putIfAbsent(borrowedBook.getBook().getIsbn(),borrowedBook);
    }
    public void removeBorrowedBook(String isbn) {
        borrowedBookMap.remove(isbn);
    }

}

