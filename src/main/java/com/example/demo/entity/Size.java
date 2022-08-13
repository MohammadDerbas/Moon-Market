package com.example.demo.entity;

import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Size")
@Table(name="size",uniqueConstraints = @UniqueConstraint(name="size_unique",columnNames = "size"))
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false  )
    private long id;
    @Column(name = "size",nullable = false)
    @JsonView(View.View2.class)
    private String size;

    public Size() {
    }

    public Size( String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
    @ManyToMany(
            mappedBy = "sizes"

    )
    @JsonIgnore
    private List <Product> products=new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public void addProduct(Product product){
            products.add(product);
            products.get(products.size()-1).addSize(this);

    }
    public void addProductB(Product product){
            products.add(product);

            product.addSize(this);
            System.out.println(product.getSizes()+"333333333333333333333333");

    }
   /* public void removeProduct(Product product){
        if(products.contains(product)){
            products.remove(product);
            product.setSize(null);
        }
    }*/


    @Override
    public String toString() {
        return "Size{" +
                "id=" + id +
                ", size='" + size + '\'' +
                '}';
    }

    public void setType(String size) {
        this.size = size;
    }
}
