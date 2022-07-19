package com.example.demo.entity;

import javax.persistence.Entity;

@Entity(name="DelivaryMan")
public class DelivaryMan extends User{
    public DelivaryMan() {
    }

    public DelivaryMan(String userName, String firstName, String lastName, String email, String password, String address, String phone, String postalCode) {
        super(userName, firstName, lastName, email, password, address, phone, postalCode);
    }
}
