package com.revature.water_world.ui;

import com.revature.water_world.models.*;

import java.util.Scanner;
import java.util.List;

import java.util.UUID;
import com.revature.water_world.utils.database.*;
import com.revature.water_world.utils.custom_exceptions.*;
import com.revature.water_world.daos.*;
import com.revature.water_world.services.*;

public class LoginMenu {
    private final UserService userService;


    public LoginMenu(UserService userService) {
        this.userService = userService;
    }
    //Login Menu constructor



    public void start() { //Starts the Login ui
        String choice = "";
        Scanner uInput = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("Welcome to Water World!\n(1) Sign Up \n(2) Login \n(Q) Quit");

                System.out.print("\nEnter: ");
                choice = uInput.nextLine();

                switch (choice) {

                    case "1":
                        signup();
                        break;
                    case "2":
                        login();

                        break;
                    case "q":
                        System.out.println("\nSee ya!");
                        System.exit(0);
                        break exit;
                    default:
                        System.out.println("\nInvalid input, please select again!");
                        break;
                }
            }
        }
    }

    private Account signup() { //Class method for signing up as a new user
        String username = "";
        String password = "";
        String password2 = "";


        int priv = 1;

        Scanner input = new Scanner(System.in);

        System.out.println("\nCreating account.");

        exit:
        {
            while (true) {
                usernameExit:
                {
                    while (true) {
                        System.out.print("\nEnter a username: ");
                        username = input.nextLine();
                        System.out.print("\nEnter a password: ");
                        password = input.nextLine();
                        //userService.isValidPassword(password);


                        System.out.print("\nEnter password again: ");
                        password2 = input.nextLine();



                        try {   //Try block tests the username and password
                            userService.isValidUsername(username);
                            userService.isDuplicateUsername(username);
                            userService.isValidPassword(password);
                            userService.isSamePassword(password, password2);
                            break usernameExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }



                confirmExit: {
                    while (true) {
                        System.out.println("\nIs this information correct? (y/n)");
                        System.out.println("Username: " + username + "\nPassword: " + password);
                        System.out.print("\nEnter: ");

                        switch (input.nextLine().toLowerCase()) {
                            case "y":
                                Account newAccount = new Account(UUID.randomUUID().toString(), username, password, priv);
                                userService.saveAcc(newAccount);
                                login();
                                return newAccount;
                            case "n":
                                System.out.println("\nRestarting.");
                                break confirmExit;
                            default:
                                System.out.println("\nInvalid input!");
                                break;
                        }
                    }
                }
            }
        }
    }

    private void login() {  //Class method for logging in
        String username = "";
        String password = "";
        Scanner scan = new Scanner(System.in);

        System.out.println("\nLogging in...");

        exit: {
            while (true) {
                System.out.print("\nEnter username: ");
                username = scan.nextLine();

                System.out.print("\nEnter password: ");
                password = scan.nextLine();

                try {
                    Account account = userService.login(username, password);
                    if (account.getPrivilege() == 2) new Admin(account, new UserService(new AccountDAO()),new ProductService(new ProductDAO()),new SupplierService(new SupplierDAO())).start();
                    else new Home(account, new UserService(new AccountDAO()), new ProductService(new ProductDAO()),new OrderService(new OrderDAO()),new InfoService(new InfoDAO())).start();

                    break exit;
                } catch (InvalidUserException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

}
