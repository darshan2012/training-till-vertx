import java.util.List;

public class Student extends User{

    List<BorrowBook> borrowedBooks;

    public Student(String username, String password, String name) {
        super(username, password, name);
    }

    public List<BorrowBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void addBookToBorrowedBook(BorrowBook book){
        borrowedBooks.add(book);
    }
    public void removeBookFromBorrow(BorrowBook borrowBook){
        borrowedBooks.remove(borrowBook);
    }

    public void setBorrowedBooks(List<BorrowBook> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
