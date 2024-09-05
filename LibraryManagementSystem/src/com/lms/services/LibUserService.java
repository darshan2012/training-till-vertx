package com.lms.services;

import com.lms.db.BooksDB;
import com.lms.models.Book;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class  LibUserService {

    public static List<Book> getAllBooks(){
        return BooksDB.getAllBooks();
    }

    public List<Book> searchBooksByName(String name){
        return BooksDB.searchBook("name",name);
    }

    public List<Book> searchBooksByAuthor(String author){
        return BooksDB.searchBook("author",author);
    }

    public Book getBookByIsbn(String isbn) {
        return BooksDB.getBookByIsbn(isbn);
    }

    public List<Book> getBooksByGenre(String genre){
        return BooksDB.searchBook("genre",genre);
    }

    public Map<String,List<Book>> viewBookByGenre(){
       return BooksDB.viewGenreWiseBooks();
    }

}
