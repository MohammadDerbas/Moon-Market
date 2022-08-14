package com.example.demo.controller;

import com.example.demo.DTO.CommentDTO;
import com.example.demo.DTO.ImageDto;
import com.example.demo.entity.Customer;
import com.example.demo.services.CustomerServices;
import com.example.demo.services.FollowServices;
import com.example.demo.services.OrderServices;
import com.example.demo.services.PurchaseServices;
import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customer")
public class CustomerController {
    private final CustomerServices customerServices;
    private final FollowServices followServices;
    private final OrderServices orderServices;
    private final PurchaseServices purchaseServices;

    @Autowired
    public CustomerController(CustomerServices customerServices, FollowServices followServices, OrderServices orderServices, PurchaseServices purchaseServices) {
        this.customerServices = customerServices;
        this.followServices = followServices;
        this.orderServices = orderServices;
        this.purchaseServices = purchaseServices;
    }

    @GetMapping("/{id}/info")
    @PreAuthorize("hasAuthority('CUSTOMER_READ')")

    @JsonView(View.View3.class)
    public Optional<Customer> infoCustomer(@PathVariable Long id) {
        return customerServices.getInfoCustomer(id);
    }

    @PreAuthorize("hasAuthority('CUSTOMER')and #id==authentication.principal.id")
    @PutMapping("/{id}/update")
    public void updateCustomerInfo(@PathVariable Long id,
                                   @RequestParam(required = false) String firstName,
                                   @RequestParam(required = false) String lastName,
                                   @RequestParam(required = false) String email,
                                   @RequestParam(required = false) String password,
                                   @RequestParam(required = false) String address,
                                   @RequestParam(required = false) String phone,
                                   @RequestParam(required = false) String postalCode) {
        customerServices.updateCustomerInfo(id, firstName, lastName, email, password, address, phone, postalCode);
    }
    @PreAuthorize("hasAuthority('CUSTOMER')and #id==authentication.principal.id")
    @GetMapping("/{id}/order-product/{id2}")
    public void orderProduct(@PathVariable Long id, @PathVariable Long id2,@RequestParam(required = true) Integer quantity) {
        customerServices.orderProduct(id, id2,quantity);
    }
    @PreAuthorize("hasAuthority('CUSTOMER')and #id==authentication.principal.id")
    @DeleteMapping("/{id}/delete-order/{id2}")
    public void deleteOrder(@PathVariable Long id, @PathVariable Long id2) {
        customerServices.deleteOrder(id, id2);
    }
    @PreAuthorize("hasAuthority('CUSTOMER')and #id==authentication.principal.id")

    @GetMapping("/{id}/orders")
    public List showOrders(@PathVariable Long id) {
        return customerServices.showOrders(id);
    }

    @GetMapping("/{id}/following")
    @PreAuthorize("hasAuthority('CUSTOMER_READ')")
    @JsonView(View.View1.class)
    public List showFollowing(@PathVariable Long id){
        return customerServices.showCustomerFollowing(id);
    }

    @GetMapping("/{id}/liked-product")
    @PreAuthorize("hasAuthority('CUSTOMER_READ')")
    @JsonView(View.View2.class)
    public List showlikedProduct(@PathVariable Long id){
        return customerServices.showCustomerLikedProduct(id);
    }


    @PreAuthorize("hasAuthority('CUSTOMER')and #id==authentication.principal.id")
    @GetMapping("/{id}/comment-seller/{id2}")
    public void commentSeller(@PathVariable Long id, @PathVariable Long id2, @RequestBody CommentDTO comment){
        customerServices.commentSeller(id,id2,comment);
    }
    @PreAuthorize("hasAuthority('CUSTOMER')and #id==authentication.principal.id")
    @GetMapping("/{id}/comment-product/{id2}")
    public void commentProduct(@PathVariable Long id, @PathVariable Long id2, @RequestBody CommentDTO comment){
        customerServices.commentProduct(id,id2,comment);
    }
    @PutMapping("/{id}/update-order-quantity/{id2}")
    public void updateQuantity(@PathVariable Long id, @PathVariable Long id2,@RequestParam(required = true) Integer quantity){
        orderServices.updateQuantity(quantity,id,id2);
    }
    @PreAuthorize("hasAuthority('CUSTOMER')and #id==authentication.principal.id")
    @GetMapping("/{id}/purchases")
    public List showCustomerPurchase(@PathVariable Long id ){
        return purchaseServices.showCustomerPurchases(id);
    }
    @PreAuthorize("hasAuthority('CUSTOMER') and #id==authentication.principal.id")
    @PostMapping("{id}/update-image")

    public void updateCustomerImage(@PathVariable Long id, @RequestBody ImageDto image){
        customerServices.updateCustomerImage(id,image);
    }



}