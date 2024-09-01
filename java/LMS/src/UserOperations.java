import com.lms.Exceptions.BookNotFoundException;

public interface UserOperations {
    String issueBook(String isbn) throws BookNotFoundException;
    String returnBook(String isbn) throws   BookNotFoundException;
    User searchUserById(String userId);
    Book searchBookByISBN(String isbn);
}