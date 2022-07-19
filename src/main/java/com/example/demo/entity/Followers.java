package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="Followers")
@Table(name="followers")
public class Followers {
    @EmbeddedId
    private FollowersId id;
    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(
            name="customer_id",
            foreignKey = @ForeignKey(name="customer_follow_id_fk")
    )
    private Customer customer;
    @ManyToOne
    @MapsId("sellerId")
    @JoinColumn(
            name = "seller_id",
            foreignKey = @ForeignKey(name="seller_followers_id_fk")
    )
    private Seller seller;
    @Column(
            name="date"
    )
    private LocalDate date;

    public Followers() {
    }

    public Followers(FollowersId id, Customer customer, Seller seller) {
        this.id = id;
        this.customer = customer;
        this.seller = seller;
        this.date = LocalDate.now();
    }

    public Followers(Customer customer, Seller seller) {
        this.customer = customer;
        this.seller = seller;
        this.date = LocalDate.now();
    }

    public FollowersId getId() {
        return id;
    }

    public void setId(FollowersId id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
