package com.example.demo.entity;

import javax.persistence.Entity;

@Entity(name="Admin")
public class Admin extends User{
    public Admin(String userName, String firstName, String lastName, String email, String password, String address, String phone, String postalCode) {
        super(userName, firstName, lastName, email, password, address, phone, postalCode);
    }
}
