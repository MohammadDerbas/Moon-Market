package com.example.demo.entity;



import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity(name="User")
@Table(name="market_user" , uniqueConstraints = {
        @UniqueConstraint(name="user_username_unique",columnNames = "user_name"),
        @UniqueConstraint(name="user_email_unique",columnNames = "email")
})
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {


    @Id
    @Column(name="id",updatable = false)


    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;
    @Column(
            name="user_name",
            nullable = false
    )
    @JsonView({View.View1.class, View.View3.class})
    private String userName;
    @Column(
            name="first_name",
            nullable = false
    )
    @JsonView({View.View1.class, View.View3.class})

    private String firstName;
    @Column(
            name="last_name",
            nullable = false
    )
    @JsonView({View.View1.class, View.View3.class})

    private String lastName;
    @Column(
            name="email",
            nullable = false
    )
    @JsonView({View.View1.class, View.View3.class})

    private String email;
    @Column(
            name="password",
            nullable = false

    )
    @JsonView({View.View1.class, View.View3.class})

    private String password;
    @Column(
            name="address",
            nullable = false
    )
    @JsonView({View.View1.class, View.View3.class})

    private String address;
    @Column(

            name="phone",
            nullable = false


    )
    @JsonView({View.View1.class, View.View3.class})

    private String phone;
    @Column(
            name="postal_code",
            nullable = false

    )
    @JsonView({View.View1.class, View.View3.class})

    private String postalCode;

    @ManyToOne
    @JoinColumn(
            name="role_id",
            referencedColumnName = "role_id",
            nullable = false,
            foreignKey = @ForeignKey(name="user_role_id_fk")
    )
    @JsonIgnore
    private Role role;


    public User() {
    }

    public User(String userName, String firstName, String lastName, String email, String password, String address, String phone, String postalCode) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.postalCode = postalCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public void addSellerComment(SellerComment sellerComment){

    }
    public void addProductComment(ProductComment productComment){

    }
    public void addToCart(Order order){

    }
    public void addPurchase(Purchase purchase){

    }
    public void follow(Followers followers){

    }
    public void addProduct(StoreHouse storeHouse){

    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
