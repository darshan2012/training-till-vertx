public interface AdminOperations {
    void addBook(Book book);
    void removeBook(String isbn);
    void removeUser(String username);
}
