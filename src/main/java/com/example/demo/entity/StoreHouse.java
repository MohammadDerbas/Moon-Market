package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "StoreHouse")
@Table(name="store_house")
public class StoreHouse {
    @EmbeddedId

    private StoreHouseId id;
    @ManyToOne
    @MapsId("sellerId")
    @JoinColumn(
            name="seller_id",
            foreignKey = @ForeignKey(name = "seller_id_fk")
    )
    private Seller seller;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(
            name="product_id",
            foreignKey = @ForeignKey(name = "product_id_fk")
    )
    private Product product;
    @Column(
            name="date"
    )
    private LocalDate date;

    public StoreHouse() {
    }

    public StoreHouse(StoreHouseId id, Seller seller, Product product) {
        this.id = id;
        this.seller = seller;
        this.product = product;
        this.date = LocalDate.now();
    }

    public StoreHouseId getId() {
        return id;
    }

    public void setId(StoreHouseId id) {
        this.id = id;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
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

    @Override
    public String toString() {
        return "StoreHouse{" +
                ", seller=" + seller +
                ", product=" + product +
                ", date=" + date +
                '}';
    }
}
