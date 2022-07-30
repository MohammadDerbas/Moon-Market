package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="Purchase")
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="customer_id")
    private Long customerId;
    @Column(name="product_id")
    private Long productId;

    private LocalDate date;
    private Double price;
    private Integer quantity;

    public Purchase() {
    }

    public Purchase(Long customerId, Long productId, LocalDate date, Double price, Integer quantity) {
        this.customerId = customerId;
        this.productId = productId;
        this.date = date;
        this.price = price;
        this.quantity = quantity;
    }

    public Purchase(Long customerId, Long productId) {
        this.customerId = customerId;
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
