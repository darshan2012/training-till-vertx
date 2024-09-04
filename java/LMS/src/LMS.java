import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class LMS {
    BufferedReader br;
    AdminOperations adminOperations;
    StudentOperations studentOperations;
    AuthActivity authActivity = new AuthActivity();
    public void loginChoice(){
        System.out.println("\n1.Login\n2.Registration\n3.Exit\n");
    }

    public LMS(){
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    public void start() {
        int choice=0;
        do{
            try{
                loginChoice();
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(br.readLine());
                switch (choice){
                    case 1 -> login();
                    case 2 -> register();
                    case 555-> adminLogin();
                    case 3 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice!");
                }
            }
            catch (Exception e){
                System.out.println("Invalid choice!");
            }
        }while(choice != 3);

    }

    private void adminLogin() {
        System.out.println("\n\t\tLogin successful!");
        libraryOperations(new Admin("admin","admin","admin"));
    }

    private void libraryOperations(User user){

        if(user instanceof Admin)
            new AdminOperationsUi(user).operations();
        else if(user instanceof Student) {
            new StudentOperationsUi(user).operations();
        }
        int choice=-1;
        do{
            try{
                System.out.println("\n1.Issue Book\n2.Return Book\n3.Search Book\n4.View All Books\n5.Exit\n");
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(br.readLine());
                switch (choice){
                    case 1 -> IssueBook((Student) user);
                    case 2 -> returnBook();
                    case 3 -> searchBook(user);
                    case 4 -> viewAllBooks(user);
                    case 0 -> System.out.println("Exiting...");
                    default -> System.err.println("Invalid choice!");
                }
            }
            catch (Exception e){
                System.err.println("Invalid choice!");
            }finally {
            }
        }while(choice != 0);
    }

    private void viewAllBooks(User user) {
        if(user instanceof Admin)
            adminOperations.getAllBooks();
        else if(user instanceof Student)
            studentOperations.getAllBooks();
    }

    private void searchBook(User user) {

    }

    private void returnBook() {

    }

    private void IssueBook(Student user) {
        System.out.print("\nEnter isbn: ");
        try {
            String isbn = br.readLine();
            studentOperations.issueBook(isbn,user.getUsername());
        } catch (IOException e) {
            System.out.println("Could not read isbn");
        }catch (Exception e)
        {
            System.out.println("Something went wrong");
        }
    }

    private void register() {
        try {
            System.out.println("\n\t\t*Register");
            System.out.print("\nEnter username: ");
            String username = br.readLine();
            System.out.print("Enter password: ");
            String password = br.readLine();
            System.out.print("Enter name: ");
            String name = br.readLine();
            boolean success = authActivity.registerUser(username, password, name);
            if(success)
            {
                System.out.println("\n\n\tRegistration Successful!");
                login();
            }
        }catch (Exception e){
            System.err.println("\nCould not Register: " + e.getMessage());
        }
    }

    private void login() {
        try {
            System.out.println("\n\t\t*Login\n");
            System.out.print("\nEnter username: ");
            String username = br.readLine();
            System.out.print("Enter password: ");
            String password = br.readLine();
            User user = authActivity.signIn(username,password);
            if(user != null)
            {
                System.out.println("\n\t\tLogin successful!");
                libraryOperations(user);
            }
        }catch (Exception e){
            System.err.println("\nCould not login: " + e.getMessage());
        }
    }
}
