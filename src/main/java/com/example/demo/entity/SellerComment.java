package com.example.demo.entity;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="SellerComment")
@Table (name="seller_comment")
public class SellerComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            name = "comment"
    )
    private String comment;
    @Column(
            name = "time"
    )
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(
            name = "seller_id",
            foreignKey = @ForeignKey(name = "seller_id_fk")
    )
    private Seller seller;
    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            foreignKey = @ForeignKey(name = "customer_id_fk")
    )
    private Customer customer;

    public SellerComment() {
    }

    public SellerComment( String comment) {
        this.comment = comment;
        this.time=LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }



    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
