package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="Cart")
@Table(name="cart")
public class Order {
    @EmbeddedId
    private OrderId id;
    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "customer_id",
            foreignKey = @ForeignKey(name="order_customer_id_fk"))

    private Customer customer;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id",
            foreignKey = @ForeignKey(name="order_product_id_fk"))
    private Product product;
    @Column(
            name="date"
    )
    private LocalDate date;
    private Integer quantity;
    private Double price;

    public Order() {
    }

    public Order(Customer customer, Product product,Integer quantity) {
        this.customer = customer;
        this.product = product;
        this.date = LocalDate.now();
        this.quantity=quantity;
        this.price=product.getPrice()*quantity;
    }

    public Order(OrderId id, Customer customer, Product product,Integer quantity) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.date = LocalDate.now();
        this.quantity=quantity;
        this.price=product.getPrice()*quantity;
    }

    public OrderId getId() {
        return id;
    }

    public void setId(OrderId id) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
