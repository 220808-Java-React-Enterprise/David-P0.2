package com.revature.water_world;

import com.revature.water_world.daos.*;
import com.revature.water_world.services.UserService;
import com.revature.water_world.ui.LoginMenu;
import com.revature.water_world.utils.custom_exceptions.*;
import com.revature.water_world.utils.database.ConnectionFactory;


public class Main {
    public static void main(String[] args) {

        AccountDAO accountDAO = new AccountDAO();
        UserService userService = new UserService(accountDAO);
        new LoginMenu(userService).start();


    }
}
