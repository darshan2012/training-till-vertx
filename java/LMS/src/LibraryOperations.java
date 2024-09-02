
import com.lms.Exceptions.BookNotFoundException;
import com.lms.Exceptions.BookUnavailableException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LibraryOperations implements StudentOperations,AdminOperations  {
    public final long MAX_HOLD_DAYS = 14;
    public final long PENALTY_PER_DAY = 10;
    Map<String, Book> books = new HashMap<>();
    Map<String,BorrowBook> borrowedBooks = new HashMap<>();
    Map<String,Student> users;
    PenaltyCalculator penaltyCalculator;

    public LibraryOperations(Map<String, Student> users, PenaltyCalculator calculator) {
        this.users = users;
        this.penaltyCalculator = calculator;
    }

    public LibraryOperations(Map<String, User> users){

        PenaltyCalculator penaltyCalculator = (dueDate, returnDate) ->
            Duration.between(dueDate,returnDate).toDays() * PENALTY_PER_DAY;
    }

    @Override
    public void addBook(Book book){
        if (checkIfIsbnExist(book.getIsbn())) {
            throw new RuntimeException("Book Already Exist!");
        }
        books.put(book.getIsbn(),book);
    }

    @Override
    public void addBook(String isbn,String name,String author,String genre)
    {
        if (checkIfIsbnExist(isbn)) {
            throw new RuntimeException("Book Already Exist!");
        }
//        books.computeIfAbsent(isbn,(k) -> new Book(isbn,name,author,genre));
        books.put(isbn,new Book(isbn,name,author,genre));
    }

    @Override
    public boolean checkIfIsbnExist(String isbn){
        return books.containsKey(isbn);
    }

    @Override
    public void removeBook(String isbn){
        if (!checkIfIsbnExist(isbn)) {
            throw new BookNotFoundException();
        }
        if(books.get(isbn).getIsAvailable())
            books.remove(isbn);
        else
            throw new BookUnavailableException();
    }

    @Override
    public void removeUser(String username) {

    }

    @Override
    public Book issueBook(String isbn,String username){
        Book book = books.get(isbn);
        if(!book.getIsAvailable())
        {
            throw new BookUnavailableException();
        }
        BorrowBook borrowBook = new BorrowBook(book);
        borrowedBooks.put(isbn,borrowBook);
        users.get(username).addBookToBorrowedBook(borrowBook);
        book.setIsAvailable(false);
        return book;
    }

    @Override
    public long returnBook(String isbn,String username){
        if (!checkIfIsbnExist(isbn)) {
            throw new BookNotFoundException();
        }
        Book book = books.get(isbn);
        book.setIsAvailable(true);
        //calculate penalty
        long penalty = penaltyCalculator.calculatePenalty(borrowedBooks.get(isbn).getIssueDate().plusDays(MAX_HOLD_DAYS),LocalDateTime.now());
        users.get(username).removeBookFromBorrow(borrowedBooks.get(isbn));
        borrowedBooks.remove(isbn);
        return penalty;
    }

    @Override
    public List<Book> searchBooksByName(String name){
        List<Book> result = books.values().stream()
                .filter(entry -> entry.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public Book searchBooksByisbn(String isbn){
        return books.get(isbn);
    }

    @Override
    public List<Book> searchBooksByauthor(String author){
        List<Book> result = books.values().stream()
                .filter(entry -> entry.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .toList();
        return result;
    }

    @Override
    public List<Book> getBookByGenre(String genre){
        List<Book> result = books.values().stream()
                .filter(entry -> entry.getGenre().toLowerCase().contains(genre.toLowerCase()))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public Map<String,List<Book>> viewBookByGenre(){
        var result = books.values().stream().collect(Collectors.groupingBy(Book::getGenre));
        return result;
    }

    @Override
    public List<Book> getAllBooks(){
        return books.values().stream().toList();
    }
}
