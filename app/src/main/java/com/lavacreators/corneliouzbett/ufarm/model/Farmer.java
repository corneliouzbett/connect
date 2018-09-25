package com.lavacreators.corneliouzbett.ufarm.model;

public class Farmer {
    String firstname;
    String lastname;
    String phone;
    String location;
    String email;
    String typeoffarming;
    String nearestTown;
    String description;

    public Farmer() {
    }

    public Farmer(String firstname, String lastname, String phone, String location, String email,
                  String typeoffarming, String nearestTown, String description) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.location = location;
        this.email = email;
        this.typeoffarming = typeoffarming;
        this.nearestTown = nearestTown;
        this.description = description;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTypeoffarming() {
        return typeoffarming;
    }

    public void setTypeoffarming(String typeoffarming) {
        this.typeoffarming = typeoffarming;
    }

    public String getNearestTown() {
        return nearestTown;
    }

    public void setNearestTown(String nearestTown) {
        this.nearestTown = nearestTown;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
