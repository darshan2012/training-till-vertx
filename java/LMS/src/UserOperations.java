import com.lms.Exceptions.BookNotFoundException;

import java.util.List;
import java.util.Map;

public interface UserOperations {
    public boolean checkIfIsbnExist(String isbn);
    List<Book> searchBooksByName(String name);
    Book searchBooksByisbn(String isbn);
    List<Book> searchBooksByauthor(String author);
    List<Book> getBookByGenre(String genre);
    Map<String,List<Book>> viewBookByGenre();
    List<Book> getAllBooks();
}