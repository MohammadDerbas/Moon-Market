package com.example.demo.entity;

import javax.persistence.*;

@Entity(name="ProductLike")
@Table(name="product_like")
public class Like {
    @EmbeddedId
    private LikeId id;
    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(
            name="customer_id",
            foreignKey = @ForeignKey(name="customer_like_id_fk")
    )
    private Customer customer;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(
            name = "product_id",
            foreignKey = @ForeignKey(name="product_like_id_fk")
    )
    private Product product;

    public Like() {
    }

    public Like(LikeId id, Customer customer, Product product) {
        this.id = id;
        this.customer = customer;
        this.product = product;
    }

    public Like(Customer customer, Product product) {
        this.customer = customer;
        this.product = product;
    }

    public LikeId getId() {
        return id;
    }

    public void setId(LikeId id) {
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
}
