package com.revature.water_world.services;

import com.revature.water_world.models.*;
import com.revature.water_world.daos.*;
import com.revature.water_world.utils.custom_exceptions.*;

import java.util.List;


public class UserService {
    private final AccountDAO accountDAO;




    public UserService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;

    }


    public Account login(String username, String pass) {
        Account account = AccountDAO.getUserByUsernameAndPassword(username, pass);

        if (account == null) throw new InvalidUserException("\nIncorrect username or password.");
        return account;
    }

    public boolean isDuplicateUsername(String username) {
        if (accountDAO.getUsername(username) != null) throw new InvalidUserException("\nSorry, " + username + " already been taken :(");
        return false;
    }

    public boolean isSamePassword(String pass, String pass2) {
        if (!pass.equals(pass2)) throw new InvalidUserException("\nPassword do not match");
        return true;
    }

    public boolean isValidUsername(String username) {
        if (!username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$"))
            throw new InvalidUserException("\nInvalid username! Username must be 8-20 characters long. no _ or . at the beginning. no __ or _. or ._ or .. inside!");
        return true;
    }

    public boolean isValidPassword(String pass) {
        if (pass.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) throw new InvalidUserException("\nInvalid password! Minimum eight characters, at least one letter, one number, and a special character include a special character(@,$,#.etc).");
        return true;
    }

    public void saveAcc(Account account) {
        accountDAO.saveAccount(account);
    }

    /*public List<Account> getAllAccounts() {
        return accountDAO.getAll();
    }*/
}
