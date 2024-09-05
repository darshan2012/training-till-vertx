package com.lms.services;

import com.lms.db.BorrowersDB;
import com.lms.exceptions.AuthenticationException;
import com.lms.models.Borrower;

import java.util.Map;

public class AuthenticationService {

    private static Boolean checkPassword() {
        return true;
    }

    public static Borrower signIn(Borrower user){
        return signIn(user.getUsername(), user.getPassword());
    }

    public static Borrower signIn(String username, String password) {

        Borrower borrower = BorrowersDB.getBorrowerById(username);
        if(borrower == null)
            throw new RuntimeException("Invalid username or password");
        if (!BorrowersDB.getBorrowerById(username).getPassword().equals(password)) {
            throw new RuntimeException("Invalid username or password");
        }
        return borrower;
    }

    public static Boolean registerUser(Borrower user) {
        return registerUser(user.getUsername(),user.getPassword(),user.getName());
    }

    public static Boolean registerUser(String username, String password, String name){

        if(BorrowersDB.checkIfUserExist(username))
        {
            throw new AuthenticationException("User Already Exist");
        }
        if (!checkPassword()) {
            throw new AuthenticationException("Password regex does not match");
        }
        BorrowersDB.addBorrower(username,password,name);
        return true;
    }
}
