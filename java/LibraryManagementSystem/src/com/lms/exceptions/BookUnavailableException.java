package com.lms.exceptions;

public class BookUnavailableException extends RuntimeException {
    public BookUnavailableException(){
        super("Book is not available");
    }
    public BookUnavailableException(String message){
        super(message);
    }
}
