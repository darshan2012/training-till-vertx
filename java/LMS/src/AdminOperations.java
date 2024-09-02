public interface AdminOperations extends UserOperations{
    void addBook(Book book);
    void addBook(String isbn,String name,String author,String genre);
    void removeBook(String isbn);
    void removeUser(String username);
}
