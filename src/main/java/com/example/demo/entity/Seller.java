package com.example.demo.entity;

import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name="Seller")
@Table(name="seller")
public class Seller extends User{
    @Id
    @Column(
            name="id",
            updatable = false
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.View1.class)

    private Long id;

    @Column(
            name="report_counter"
    )
    private Integer reportCounter;
    @Column(
            name="star"
    )
    @JsonView(View.View1.class)
    private Integer star;
    @Column(
            name="acticate"
    )
    private Boolean activate;
    @OneToMany(
            mappedBy = "seller"
    )
    @JsonIgnore
    private List <SellerComment> sellerComments=new ArrayList<>();
    @OneToMany(
            mappedBy = "seller"
    )
    private List <Followers> followers=new ArrayList<>();
    @OneToMany(
           mappedBy = "seller"
    )
    private List <StoreHouse> storeHouses=new ArrayList<>();


    public Seller() {}

    public Seller(String firstName, String lastName, String email, String password, String address, String phone, String postalCode, Collection<Role> roles,String profilePic) {
        super( firstName, lastName, email, password, address, phone, postalCode,roles,profilePic);
        this.reportCounter = 0;
        this.star = 0;
        this.activate = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getReportCounter() {
        return reportCounter;
    }

    public void setReportCounter(Integer reportCounter) {
        this.reportCounter = reportCounter;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Boolean getActivate() {
        return activate;
    }

    public void setActivate(Boolean activate) {
        this.activate = activate;
    }

    public List<SellerComment> getSellerComments() {
        return sellerComments;
    }

    public void setSellerComments(List<SellerComment> sellerComments) {
        this.sellerComments = sellerComments;
    }


    public void addProduct(StoreHouse storeHouse){
        storeHouses.add(storeHouse);

    }
    public void removeProduct(StoreHouse storeHouse) {
        storeHouses.remove(storeHouse);
    }
}
