package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="Purchase")
@Table(name = "purchase")
public class Purchase {
    @EmbeddedId
    private PurchaseId id;
    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "customer_id",
    foreignKey = @ForeignKey(name="purchase_customer_id_fk"))
    private Customer customer;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id",
            foreignKey = @ForeignKey(name="purchase_product_id_fk"))
    private Product product;
    @Column(
        name = "purchase_at"
    )
    private LocalDate purchaseAt;

    public Purchase() {
    }

    public Purchase(PurchaseId id, Customer customer, Product product) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.purchaseAt=LocalDate.now();
    }

    public Purchase(Customer customer, Product product) {
        this.customer = customer;
        this.product = product;
    }

    public PurchaseId getId() {
        return id;
    }

    public void setId(PurchaseId id) {
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

    public void setPurchaseAt(LocalDate purchaseAt) {
        this.purchaseAt = purchaseAt;
    }

    public LocalDate getPurchaseAt() {
        return purchaseAt;
    }
}
