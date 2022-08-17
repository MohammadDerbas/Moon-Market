package com.example.demo.entity;

import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Type")
@Table(name = "type",uniqueConstraints = @UniqueConstraint(name="type_unique",columnNames = "type"))
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false  )
    private long id;
    @Column(name = "type",nullable = false)
    @JsonView({View.View2.class,View.View4.class})
    private String type;

    public Type() {
    }

    public Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @OneToMany(
            mappedBy = "type",
            //cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
            orphanRemoval = true,
            fetch = FetchType.LAZY


    )


    private List<Product>products=new ArrayList<>();

    public void addProduct(Product product){
        if(!products.contains(product)){
            products.add(product);
            product.setType(this);
        }
    }
    public void removeProduct(Product product){
        if(products.contains(product)){
            products.remove(product);
            product.setType(null);
        }
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
