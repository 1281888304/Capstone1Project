package com.example.logindatabase;

public class User {

    public String fullName,phoneNumber,email,address;

    public User(){

    }

    public User(String fullName, String phoneNumber, String email, String address) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }
}
