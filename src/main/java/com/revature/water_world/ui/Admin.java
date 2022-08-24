package com.revature.water_world.ui;

import com.revature.water_world.services.*;
import com.revature.water_world.models.*;
import com.revature.water_world.daos.*;
import java.util.*;
import java.util.UUID;

public class Admin implements StartMenu{

    private final Account account;
    private final UserService userService;
    private final ProductService prodService;
    private final SupplierService supService;

    public Admin(Account account, UserService userService, ProductService prodService, SupplierService supService) {
        this.account = account;
        this.userService = userService;
        this.prodService = prodService;
        this.supService = supService;
    }   //Constructor for admin

    @Override
    public void start() { //Overriden start method which initiates the admin ui
        String choice = "";
        System.out.println("\nWelcome back boss! " + account.getUsername() + "!");
        System.out.println("\n[1] Update/View inventory \n[2]View suppliers \n[3]Add supplier \n[4]Sign out");
        Scanner input = new Scanner(System.in);
        System.out.println("Enter: ");
        choice = input.nextLine();

        switch (choice){
            case "1":
                updateInventory();
                break;
            case "2":
                viewAllSuppliers();
                break;
            case "3":
                addNewSupp();
                break;
            case "4":
                new LoginMenu(new UserService(new AccountDAO())).start();
                break;
            default:
                System.out.println("invalid choice.");


        }

    }

    private void updateInventory(){  //Class method for updating the inventory
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("\nProducts:\tStock:");
                List<Product> products = prodService.listAllProducts();

                for (int i = 0; i < products.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + products.get(i).getName()+"\t "+products.get(i).getInventory());
                }

                System.out.print("\nSelect a product you would like to update the inventory of: ");
                int index = scan.nextInt() - 1;

                try {
                    Product selection = products.get(index);



                    System.out.println("\nProduct: " + selection.getName());


                    System.out.print("\nUpdated inventory: ");
                    //scan.nextInt();
                    int quantity = scan.nextInt();
                    selection.setInventory(quantity);
                    prodService.updateInventory(selection);


                    System.out.print("\nInventory updated.\n");





                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\nInvalid input!");
                }

                new Admin(account, new UserService(new AccountDAO()),new ProductService(new ProductDAO()),new SupplierService(new SupplierDAO())).start();
            }
            }
        }


    private void viewAllSuppliers(){  //class method for viewing all suppliers

        System.out.println("\nName:\t\tContact:\t\tEmail:");
        List<Supplier> suppliers = supService.listAllSuppliers();

        for (int i = 0; i < suppliers.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + suppliers.get(i).getName()+"\t "+suppliers.get(i).getContactName()+"\t "+suppliers.get(i).getEmail()+"\n");
        }
        System.out.println("\n\n\n");
        new Admin(account, new UserService(new AccountDAO()),new ProductService(new ProductDAO()),new SupplierService(new SupplierDAO())).start();


    }

    private void addNewSupp(){ //class method for adding a new supplier
        String sID = UUID.randomUUID().toString();
        String name1 = "";
        String cName = "";
        String pNum = "";
        String email = "";
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name of the supplier: ");
        name1 = input.nextLine();
        System.out.println("Enter contact name: ");
        cName = input.nextLine();
        System.out.println("Enter contacts phone number: ");
        pNum = input.nextLine();
        System.out.println("Enter contact's email: ");
        email = input.nextLine();

        Supplier newSup = new Supplier(sID,name1,cName,pNum,email);
        supService.newSupplier(newSup);

        System.out.println("Supplier added");
        new Admin(account, new UserService(new AccountDAO()),new ProductService(new ProductDAO()),new SupplierService(new SupplierDAO())).start();
    }
}
