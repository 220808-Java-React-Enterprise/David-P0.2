package com.revature.water_world.models;

public class Order {

    private String orderID, accountID;
    private String datePlaced, expectedArrival;
    private double cost;
    private int quantity;
    private String productID;


    public Order(){
        this.orderID = orderID;
        this.accountID = accountID;
        this.datePlaced = datePlaced;
        this.expectedArrival = expectedArrival;
        this.cost = cost;
        this.quantity = quantity;
        this.productID = productID;
    }

    public Order(String oID, String acID, String dateP, String eArrival, double cost, int q, String pID) {
        this.orderID = oID;
        this.accountID = acID;
        this.datePlaced = dateP;
        this.expectedArrival = eArrival;
        this.cost = cost;
        this.quantity = q;
        this.productID = pID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(String datePlaced) {
        this.datePlaced = datePlaced;
    }

    public String getExpectedArrival() {
        return expectedArrival;
    }

    public void setExpectedArrival(String expectedArrival) {
        this.expectedArrival = expectedArrival;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", accountID='" + accountID + '\'' +
                ", datePlaced='" + datePlaced + '\'' +
                ", expectedArrival='" + expectedArrival + '\'' +
                ", cost=" + cost +
                ", quantity=" + quantity +
                ", productID='" + productID + '\'' +
                '}';
    }
}
