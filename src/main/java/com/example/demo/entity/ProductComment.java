package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="ProductComment")
@Table(name="product_comment")
public class ProductComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(
            name="customer_id",
            foreignKey = @ForeignKey(name="customer_id_fk")
    )
    private Customer customer;
    @ManyToOne
    @JoinColumn(
            name="product_id",
            foreignKey = @ForeignKey(name="product_id_fk")
    )
    private Product product;
    @Column(
            name="time"
    )
    private LocalDateTime time;
    @Column(
            name="comment"
    )
    private String comment;
    public ProductComment() {
    }

    public ProductComment(String comment) {

        this.time = LocalDateTime.now();
        this.comment=comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getTime() {
        return time;
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
