package com.revature.water_world.ui;
import com.revature.water_world.services.*;
import com.revature.water_world.models.*;
import java.util.List;
import com.revature.water_world.daos.*;
import java.util.Scanner;
import java.util.UUID;

public class Home implements StartMenu{

    private final Account account;
    private final UserService userService;
    private final ProductService prodService;
    private final OrderService orderService;
    private final InfoService infoService;


    public Home(Account account, UserService userService, ProductService prodService, OrderService ordService, InfoService infoService) {
        this.account = account;
        this.userService = userService;
        this.prodService = prodService;
        this.orderService = ordService;
        this.infoService = infoService;
        //Constructor for Home
    }



    @Override //Overriden start method for initiating Home ui
    public void start() {
        Scanner input = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("\nWelcome home " + account.getUsername() + "!");
                System.out.println("[1] View products");
                System.out.println("[2] View order history");
                System.out.println("[3] Update account info");
                System.out.println("[x] Sign out");
                System.out.print("\nEnter: ");

                switch (input.nextLine()) {
                    case "1":
                        viewProducts();
                        break;
                    case "2":
                        viewOrderHistory();
                        break;

                    case "3":
                        updateAccountInfo();
                        break;

                    case "x":
                        new LoginMenu(userService).start();
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }

    private void viewProducts() {
        Scanner scan = new Scanner(System.in);

        //class method for simply viewing products or making a purchase

        exit: {
            while (true) {
                System.out.println("\nShop all of our wonderful products!");
                List<Product> products = prodService.listAllProducts();

                for (int i = 0; i < products.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + products.get(i).getName()+" $"+products.get(i).getPrice());
                }

                System.out.print("\nSelect a product or press x to exit: ");
                /*if (scan.next()=="x"){
                    break exit;
                }*/
                int index = scan.nextInt() - 1;

                String dateP = "August 24, 2022";
                String expA = "August 30, 2022";
                String oID = UUID.randomUUID().toString();
                Product selection = products.get(index);
                System.out.println("\nProduct: " + selection.getName());
                System.out.print("\nQuantity: ");
                String quantity = scan.next();
                int qResult = Integer.valueOf(quantity);
                double c = selection.getPrice()*qResult;
                String pID = selection.getProductID();
                String aID = account.getAccountID();
                System.out.println("\nOrder details: ");
                System.out.println(selection.getName()+"\nQuantity: "+quantity+"\nTotal: $"+c);
                Order or = new Order(oID,aID,dateP,expA,c,qResult,pID);
                System.out.print("\nConfirm purchase y/n: ");
                String ans = scan.next();
                switch(ans.toLowerCase()){
                    case "y":
                        orderService.saveOrder(or);
                        System.out.println("Purchase confirmed!");
                        break exit;
                    default:
                        break exit;

                }

            }
        }
    }
    private void viewOrderHistory() {  //Class method for viewing a users order history
        String acc = account.getAccountID();
        List<Order> orders = orderService.getAllOrdersByAccountId(acc);
        System.out.println("\nOrders for " + account.getUsername() + ":");
        for (Order x : orders) {
            System.out.println("Item: " + x.getProductID());
            System.out.println("Quantity: " + x.getQuantity());
            System.out.println("Price paid: " + x.getCost());
            System.out.println("Order placed: " + x.getDatePlaced());


        }
        Scanner input = new Scanner(System.in);
        System.out.print("Enter anything to exit: ");
        switch(input.nextLine()){
            default:
                new Home(account, new UserService(new AccountDAO()), new ProductService(new ProductDAO()),new OrderService(new OrderDAO()),new InfoService(new InfoDAO())).start();

                break;


        }
    }

    private void updateAccountInfo(){   //Class method which allows user to add personal info to their account
        Scanner input = new Scanner(System.in);
        String name1 = "";
        String email1 = "";
        String pnumber1 = "";
        String address1 = "";
        String city1 = "";
        String state1 = "";
        String zip1 = "";
        System.out.println("\nEnter your name: ");
        name1 = input.nextLine();

        System.out.println("\nEnter your email: ");
        email1 = input.nextLine();

        System.out.println("\nEnter your phone number: ");
        pnumber1 = input.nextLine();
        System.out.println("\nEnter your address: ");
        address1 = input.nextLine();
        System.out.println("\nEnter your city: ");
        city1 = input.nextLine();
        System.out.println("\nEnter your state: ");
        state1 = input.nextLine();
        System.out.println("\nEnter your zip: ");
        zip1 = input.nextLine();

        AccountInfo newInfo = new AccountInfo(UUID.randomUUID().toString(),name1,email1,pnumber1,address1,city1,state1,zip1,account.getAccountID());
        infoService.saveAcInfo(newInfo);
        System.out.println("Success account info updated!");

        new Home(account, new UserService(new AccountDAO()), new ProductService(new ProductDAO()),new OrderService(new OrderDAO()),new InfoService(new InfoDAO())).start();



    }
}

