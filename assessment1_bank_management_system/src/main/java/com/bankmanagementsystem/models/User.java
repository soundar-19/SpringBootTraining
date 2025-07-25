package com.bankmanagementsystem.models;

public class User {
    
    private String userName;
    private long mobileNumber;
    private String email;
    private String address;

    public User(String userName, long mobileNumber, String email, String address) {
        this.userName = userName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.address = address;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public long getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    
}
