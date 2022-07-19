package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="SellerComment")
@Table (name="seller_comment")
public class SellerComment {
    @EmbeddedId
    private SellerCommentId id;
    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(
            name="customer_id",
            foreignKey = @ForeignKey(name="customer_id_fk")
    )
    private Customer customer;
    @ManyToOne
    @MapsId("sellerId")
    @JoinColumn(
            name="seller_id",
            foreignKey = @ForeignKey(name="seller_id_fk")
    )
    private Seller seller;

    @Column(
            name="time"
    )
    private LocalDateTime time;
    @Column(
            name="comment"
    )
    private String comment;

    public SellerComment() {
    }

    public SellerComment(SellerCommentId id, Customer customer, Seller seller, String comment) {
        this.id = id;
        this.customer = customer;
        this.seller = seller;
        this.time = LocalDateTime.now();
        this.comment = comment;
    }

    public SellerComment(Customer customer,Seller seller,   String comment) {
        this.customer = customer;
        this.seller = seller;
        this.time = LocalDateTime.now();
        this.comment = comment;
    }

    public SellerCommentId getId() {
        return id;
    }

    public void setId(SellerCommentId id) {
        this.id = id;
    }



    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getTime() {
        return time;
    }
    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
