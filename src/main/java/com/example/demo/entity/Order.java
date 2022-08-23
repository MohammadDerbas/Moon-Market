package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

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

    private LocalDate date=LocalDate.now();
    private Integer quantity;
    private String status="PENDING";
    private Boolean isDeleted=false;
    private LocalDate deletedAt;
    private LocalDate updatedAt;
    private LocalDate deliverdAt;
    private String size;
    private  String color;

    private Double price;
    private UUID reference=UUID.randomUUID();


    public Order() {
    }

    public Order( Product product,Integer quantity,String size,String color,Double price) {
        this.product = product;
        this.date = LocalDate.now();
        this.quantity=quantity;
        this.price=price;
        this.color=color;

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

    public String getStatus() {
        return status;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public LocalDate getDeliverdAt() {
        return deliverdAt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeliverdAt(LocalDate deliverdAt) {
        this.deliverdAt = deliverdAt;
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

    public UUID getReference() {
        return reference;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", product=" + product +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}



