package com.example.demo.entity;

import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name="Customer")
@Table(name="customer")
public class Customer extends User  {


    @Id
    @Column(name="id",updatable = false)

    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;





    private Boolean activate;
    @Column(
            name="points",
            nullable = false

    )
    @JsonView( View.View3.class)
    private Integer points;
    @Column(
            name="report_counter",
            nullable = false

    )
    private Integer reportCounter;
    @Column(
            name="customer_purchases",
            nullable = false

    )
    @JsonView( View.View3.class)
    private Double customerPurchases;


    @ManyToOne
    @JoinColumn(
            name = "membership_id",
            referencedColumnName = "id",

            nullable = false,

            foreignKey =@ForeignKey(name = "customer_membership_id")



    )
    @JsonView( View.View3.class)

    private MemberShip memberShip;



    @OneToMany(
            mappedBy = "customer"
    )
    private List<Order> orders =new ArrayList<>();
    @OneToMany (
         mappedBy = "customer"
    )
    private List <Followers>followers=new ArrayList<>();
   @OneToMany(
           mappedBy = "customer"
   )
    private List <ProductComment> productComments =new ArrayList<>();

    @OneToMany(
            mappedBy = "customer"
    )
    @JsonIgnore
    private List <SellerComment> sellerComments=new ArrayList<>();
    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, String password, String address, String phone, String postalCode, Collection<Role> roles) {
        super( firstName, lastName, email, password, address, phone, postalCode,roles);
        this.activate = true;
        this.points = 0;
        this.reportCounter = 0;
        this.customerPurchases = 0.0;

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActivate() {
        return activate;
    }

    public void setActivate(Boolean activate) {
        this.activate = activate;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getReportCounter() {
        return reportCounter;
    }

    public void setReportCounter(Integer reportCounter) {
        this.reportCounter = reportCounter;
    }

    public Double getCustomerPurchases() {
        return customerPurchases;
    }

    public void setCustomerPurchases(Double customerPurchases) {
        this.customerPurchases = customerPurchases;
    }

    public void setMemberShip(MemberShip memberShip) {
        this.memberShip = memberShip;
    }



    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<ProductComment> getProductComments() {
        return productComments;
    }

    public void setProductComments(List<ProductComment> productComments) {
        this.productComments = productComments;
    }

    public MemberShip getMemberShip() {
        return memberShip;
    }

    public void addToCart(Order order){
        orders.add(order);
    }
    public void removeFromCart(Order order){
        if(orders.contains(order)){
            orders.remove(order);
        }
    }
    public void follow(Followers followers){

            this.followers.add(followers);

        }

    public void removeFollow(@NotNull Followers followers){
        if(!this.followers.contains(followers)){
            this.followers.remove(followers);

        }

    }
    public void  addProductComment(ProductComment productComment){
      productComments.add(productComment);
    }
    public void  removeProductComment(ProductComment productComment){
            productComments.remove(productComment);

    }
  /*  public void addSellerComment(SellerComment sellerComment){
        sellerComments.add(sellerComment);
    }
    public void removeSellerComment(SellerComment sellerComment){
        sellerComments.remove(sellerComment);

    }*/





}
