package com.lms.models;

import java.util.ArrayList;
import java.util.List;

public class Borrower extends User {

    List<BorrowedBook> borrowedBooks = new ArrayList<>();

    public Borrower(String username, String password, String name) {
        super(username, password, name);
    }

    public Borrower(String username, String password) {
        super(username,password,"");
    }

    public List<BorrowedBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void addBookToBorrowedBook(BorrowedBook book){
        borrowedBooks.add(book);
    }
    public void removeBookFromBorrow(BorrowedBook BorrowedBook){
        borrowedBooks.remove(BorrowedBook);
    }

    public void removeBookFromBorrow(String isbn){
        int ind=-1;
        for(int i=0;i<borrowedBooks.size();i++)
        {
            if (borrowedBooks.get(i).getBook().getIsbn().equals(isbn)) {
                ind = i;
                break;
            }
        }
        borrowedBooks.remove(ind);
    }
    public void setBorrowedBooks(List<BorrowedBook> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}

