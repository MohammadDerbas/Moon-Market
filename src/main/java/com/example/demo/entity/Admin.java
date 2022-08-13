package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;

@Entity(name = "Admin")
@Table(name="admin")
public class Admin extends User{
    public Admin() {
    }

    public Admin(String firstName, String lastName, String email, String password, String address, String phone, String postalCode, Collection<Role> roles,String profilePic) {
        super(firstName, lastName, email, password, address, phone, postalCode, roles,profilePic);
    }
}
