import com.lms.Exceptions.BookNotFoundException;

public interface StudentOperations extends UserOperations{
    Book issueBook(String isbn,String username) throws BookNotFoundException;
    long returnBook(String isbn,String username) throws   BookNotFoundException;

}
