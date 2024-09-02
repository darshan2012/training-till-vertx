import java.time.LocalDate;
import java.time.LocalDateTime;

public class BorrowBook {
    public Book getBook() {
        return book;
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

    public BorrowBook(Book book) {
        this.book = book;
        issueDate = LocalDateTime.now();
    }

    private Book book;
    private LocalDateTime issueDate;

}
