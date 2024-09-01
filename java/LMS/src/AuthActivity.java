import java.util.HashMap;
import java.util.Map;

public class AuthActivity {
    private Map<String,User> users = new HashMap<>();

    public  void registerUser(String username,String password,String name){

        if(checkIfUserExist(username))
        {
            throw new RuntimeException("User Already Exist");
        }
        if (checkPassword()) {
            throw new RuntimeException("Password regex does not match");
        }
        users.put(username,new User(username,password,name));
    }
    public Boolean checkIfUserExist(String username){
        return users.containsKey(username);
    }

    public Boolean checkPassword() {
        return true;
    }

    public User signIn(String username, String password) {
        if(!checkIfUserExist(username))
        {
            throw new RuntimeException("Invalid username or password");
        }
        if (!users.get(username).getPassword().equals(password)) {
            throw new RuntimeException("Invalid username or password");
        }
        return users.get(username);
    }
}
