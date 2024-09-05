package com.lms.server.db;

import com.lms.server.models.BorrowedBook;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BorrowedBooksDB {
    private final static Map<String, BorrowedBook> borrowedBookMap = new ConcurrentHashMap<>();
    public static boolean checkIfISBNExist(String ISBN){
        return borrowedBookMap.containsKey(ISBN);
    }
    public static void removeBook(String ISBN){
        borrowedBookMap.remove(ISBN);
    }
    public static void addBorrowedBook(BorrowedBook borrowedBook){
        borrowedBookMap.putIfAbsent(borrowedBook.getBook().getISBN(),borrowedBook);
    }
    public static void removeBorrowedBook(String ISBN) {
        borrowedBookMap.remove(ISBN);
    }
    public static BorrowedBook getBorrowedBook(String ISBN) {
        return borrowedBookMap.get(ISBN);
    }
}

