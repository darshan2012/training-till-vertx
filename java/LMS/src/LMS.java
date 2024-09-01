import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class LMS {

    public void loginChoice(){
        System.out.println("\n1.Login\n2.Registration\n3.Exit\n");
    }

    public void start() {
        int choice=0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do{
            try{
                loginChoice();
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(br.readLine());
                switch (choice){
                    case 1 -> login();
                    case 2 -> register();
                    case 3 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice!");
                }
            }
            catch (Exception e){
                System.out.println("Invalid choice!");
            }finally {

            }
        }while(choice != 3);

    }
    private void LibraryOperations(){
        int choice=-1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do{
            try{
                System.out.println("\n1.Issue Book\n2.Return Book\n3.Search Book\n4.View All Books\n5.Exit\n");
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(br.readLine());
                switch (choice){
                    case 1 -> IssueBook();
                    case 2 -> returnBook();
                    case 3 -> searchBook();
                    case 4 -> viewAllBooks();
                    case 0 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice!");
                }
            }
            catch (Exception e){
                System.out.println("Invalid choice!");
            }finally {

            }
        }while(choice != 0);
    }

    private void viewAllBooks() {

    }

    private void searchBook() {

    }

    private void returnBook() {
    }

    private void IssueBook() {
    }


    private void register() {
        try {
            System.out.println("register");
            login();
        }catch (Exception e){
            System.out.println("Could not Register.");
        }
    }

    private void login() {
        try {
            System.out.println("login");
            LibraryOperations();
        }catch (Exception e){
            System.out.println("Could not login.");
        }
    }

}
