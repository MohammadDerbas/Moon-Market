package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Seller;
import com.example.demo.entity.SellerComment;
import com.example.demo.entity.User;
import com.example.demo.services.CustomerServices;
import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customer")
public class CustomerController {
    private final CustomerServices customerServices;

    @Autowired
    public CustomerController(CustomerServices customerServices) {
        this.customerServices = customerServices;
    }
    @GetMapping("/{id}/info")
    @JsonView(View.View3.class)
    public Optional<Customer> infoCustomer(@PathVariable Long id){
        return customerServices.getInfoCustomer(id);
    }
    @PutMapping("/{id}/update")
    public void updateCustomerInfo(@PathVariable Long id,@RequestParam(required = false) String userName,
                                 @RequestParam(required = false) String firstName,
                                 @RequestParam(required = false) String lastName,
                                 @RequestParam(required = false)String email,
                                 @RequestParam(required = false) String password,
                                 @RequestParam(required = false) String address,
                                 @RequestParam(required = false) String phone,
                                 @RequestParam(required = false) String postalCode){
        customerServices.updateCustomerInfo(id,userName,firstName,lastName,email,password,address,phone,postalCode);}
        /*@GetMapping ("/{id}/sellers")
        public List<Seller> showSeller(Long id)*/

        /*@PostMapping ("/{id}/comment")
        public void commentSellerWithId(@PathVariable Long id,@RequestBody String comment){
        customerServices.commentSellerWithId(id,comment);*/
        @GetMapping("/{id}/order-product/{id2}")
        public void orderProduct(@PathVariable Long id,@PathVariable Long id2){
            customerServices.orderProduct(id,id2);
        }
    @DeleteMapping("/{id}/delete-order/{id2}")
    public void deleteOrder(@PathVariable Long id,@PathVariable Long id2){
        customerServices.deleteOrder(id,id2);
    }
    @JsonView(View.View2.class)
    @GetMapping("/{id}/orders")
        public List showOrders(@PathVariable Long id){
            return customerServices.showOrders(id);
        }

}
