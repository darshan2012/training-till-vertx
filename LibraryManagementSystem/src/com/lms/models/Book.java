package com.lms.models;

import java.io.Serializable;

public class Book implements Serializable {
    private String name;
    private String isbn;
    private String author;
    private String genre;
    private Boolean isAvailable;

    @Override
    public String toString() {

        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ",available='" + (isAvailable?"YES":"NO") + '\'' +
                '}';
    }

    public Book( String isbn,String name, String author, String genre) {
        this.name = name;
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
        this.isAvailable=true;
    }

    public Boolean getIsAvailable(){
        return isAvailable;
    }
    public void setIsAvailable(Boolean isAvailable){
        this.isAvailable = isAvailable;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


}

