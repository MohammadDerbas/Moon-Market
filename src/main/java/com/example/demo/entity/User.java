package com.example.demo.entity;



import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.*;
import java.util.*;

@Entity(name="User")
@Table(name="market_user", uniqueConstraints = {
        @UniqueConstraint(name="user_email_unique",columnNames = "email")
})
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {


    @Id
    @Column(name="id",updatable = false)


    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;

    @JsonView({View.View1.class, View.View3.class})
    private String profilePic;


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
    // @JsonView({View.View1.class, View.View3.class})

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


    private Boolean locked = false;
    private Boolean enabled = false;
    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "roleId"))
    private Collection<Role> roles;

    public User() {
    }


    public User(Collection<Role> roles) {
        this.roles = roles;
    }

    public User(String firstName, String lastName, String email, String password, String address, String phone, String postalCode,Collection<Role> roles,String profilePic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.postalCode = postalCode;
        this.roles=roles;
        this.profilePic=profilePic;


    }
    public User(String firstName, String lastName, String email, String password, String address, String phone, String postalCode,Collection<Role> roles,Boolean enabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.postalCode = postalCode;
        this.roles=roles;
        this.enabled=enabled;


    }









    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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

    public void like(Product product){


    }
    public void removeLike(@NotNull Product product){


        }



    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
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

