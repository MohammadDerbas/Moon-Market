package com.example.demo.entity;

import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Category")
@Table(name="category",uniqueConstraints = @UniqueConstraint(name="category_unique",columnNames = "category"))
public class Category {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name="id",
            updatable = false
    )
    private Long id;
    @Column(
            name="category",
            nullable = false
    )
    @JsonView({View.View2.class,View.View4.class})
    private String category;

    public Category() {
    }

    public Category(String category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @OneToMany(
            mappedBy = "category"
    )
    private List <Product> products=new ArrayList<>();
    public void addProduct(Product product){
        if(!products.contains(product)){
            products.add(product);
            product.setCategory(this);
        }

    }
    public void removeProduct(Product product){
        if(products.contains(product)){
            products.remove(product);
            product.setCategory(null);
        }
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", category='" + category + '\'' +
                '}';
    }
}
