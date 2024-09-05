package com.lms.db;

import com.lms.models.BorrowedBook;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BorrowedBooksDB {
    private static Map<String, BorrowedBook> borrowedBookMap = new ConcurrentHashMap<>();
    public static boolean checkIfIsbnExist(String isbn){
        return borrowedBookMap.containsKey(isbn);
    }
    public static void removeBook(String isbn){
        borrowedBookMap.remove(isbn);
    }
    public static void addBorrowedBook(BorrowedBook borrowedBook){
        borrowedBookMap.putIfAbsent(borrowedBook.getBook().getIsbn(),borrowedBook);
    }
    public static void removeBorrowedBook(String isbn) {
        borrowedBookMap.remove(isbn);
    }
    public static BorrowedBook getBorrowedBook(String isbn) {
        return borrowedBookMap.get(isbn);
    }
}

