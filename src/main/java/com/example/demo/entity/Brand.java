package com.example.demo.entity;

import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Brand")
@Table(name="brand",uniqueConstraints = @UniqueConstraint(name="brand_unique",columnNames = "brand"))
public class Brand {
    @Id
    @Column(
            name = "id",
            updatable = false
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            name = "brand",
            nullable = false

    )
    @JsonView(View.View2.class)
    private String brand;


    @OneToMany(
            mappedBy ="brand"

    )
    @JsonIgnore

    private List<Product> products=new ArrayList<>();

    public Brand() {
    }

    public Brand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProduct(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){
        if(!products.contains(product)){
            products.add(product);
            product.setBrand(this);
        }
    }
    public void removeProduct(Product product){
        if(products.contains(product)){
            products.remove(product);
            product.setBrand(null);
        }
    }
}
