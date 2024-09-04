package com.lms.models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BorrowedBook implements Serializable {
    public Book getBook() {
        return book;
    }

    @Override
    public String toString() {
        return "BorrowedBook{" +
                "book=" + book +
                ", issueDate=" + issueDate +
                ", expectedReturnDate=" + expectedReturnDate +
                ", returnDate=" + returnDate +
                '}';
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public BorrowedBook(Book book) {
        this.book = book;
        issueDate = LocalDateTime.now();
    }

    private Book book;
    private LocalDateTime issueDate;
    private LocalDateTime expectedReturnDate;
    private LocalDateTime returnDate;
}
