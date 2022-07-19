package com.example.demo.entity;

import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "MemberShip")
@Table(name = "member_ship")
public class MemberShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "type",
            nullable = false
    )
    @JsonView( View.View3.class)
    private String type;
    @OneToMany(mappedBy = "memberShip",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
            fetch = FetchType.LAZY)//Default
    List<Customer> customers=new ArrayList<>();

    public MemberShip() {
    }

    public MemberShip( String type) {

        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void addCustomer(Customer customer){
        if(!customers.contains(customer)){
            customers.add(customer);
            customer.setMemberShip(this);

        }
    }
    public void removeCustomer(Customer customer){
        if(customers.contains(customer)){
            customers.remove(customer);
            customer.setMemberShip(null);
        }
    }

    @Override
    public String toString() {
        return "MemberShip{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}

