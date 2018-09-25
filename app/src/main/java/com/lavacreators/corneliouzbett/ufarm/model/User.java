package com.lavacreators.corneliouzbett.ufarm.model;

public class User {
    String firstname;
    String lastname;
    String phone;
    String location;
    String accountType;
    String email;

    public User() {
    }

    public User(String firstname, String lastname, String phone, String location, String accountType, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.location = location;
        this.accountType = accountType;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
