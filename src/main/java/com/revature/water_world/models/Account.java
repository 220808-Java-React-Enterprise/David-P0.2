package com.revature.water_world.models;

public class Account {

    private String accountID;
    private String username;
    private String password;
    private int privilege;
    //private String name, phoneNumber, email, address, city, state, zip;

    public Account(){

    }

    public Account(String accountID, String username, String password, int privilege) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
        this.privilege = privilege;
    }

    /*public Account(String acID, String uName, String pass, int priv, String name, String pnum, String email, String add, String city, String state, String zip) {
        this.accountID = acID;
        this.username = uName;
        this.password = pass;
        this.privilege = priv;
        this.name = name;
        this.phoneNumber = pnum;
        this.email = email;
        this.address = add;
        this.state = state;
        this.city = city;
        this.zip = zip;
    }*/

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountID='" + accountID + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", privilege=" + privilege +
                '}';
    }
    /*public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountID=" + accountID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", privilege=" + privilege +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }*/
}
