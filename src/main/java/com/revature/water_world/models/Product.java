package com.revature.water_world.models;

public class Product {
    private String productID;
    private String name;
    private double price;
    private String description;
    private int inventory;
    private String supplierID;

    public Product(){

    }

    public Product(String productID, String name, double price, String description, int inventory, String supplierID) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.description = description;
        this.inventory = inventory;
        this.supplierID = supplierID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", inventory=" + inventory +
                ", supplierID=" + supplierID +
                '}';
    }
}
