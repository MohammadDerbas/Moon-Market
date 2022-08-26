package com.example.demo.controller;

import com.example.demo.DTO.CommentFromDto;
import com.example.demo.DTO.CustomerProductDTO;
import com.example.demo.entity.*;
import com.example.demo.repo.*;
import com.example.demo.services.PurchaseServices;
import com.example.demo.services.SellerServices;

import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("admin")
public class AdminController {
    private final CustomerRepo customerRepo;
    private final SellerRepo sellerRepo;
    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;
    private final PurchaseRepo purchaseRepo;
    private final PurchaseServices purchaseServices;

    private  final  SellerServices sellerSrvices;
    private  final SellerCommentRepo sellerCommentRepo;

    public AdminController(CustomerRepo customerRepo, SellerRepo sellerRepo, OrderRepo orderRepo, ProductRepo productRepo, PurchaseRepo purchaseRepo, PurchaseServices purchaseServices, SellerServices sellerSrvices, SellerCommentRepo sellerCommentRepo) {
        this.customerRepo = customerRepo;
        this.sellerRepo = sellerRepo;
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
        this.purchaseRepo = purchaseRepo;
        this.purchaseServices = purchaseServices;
        this.sellerSrvices = sellerSrvices;
        this.sellerCommentRepo = sellerCommentRepo;
    }

    @JsonView(View.View2.class)
    @GetMapping("/products")
    public List<Product> getProducts(){
        return productRepo.findAll();
    }
    @JsonView(View.View3.class)

    @GetMapping("/customer")

    public List<User> getCustomer(){
        return customerRepo.findAll();
    }
    @JsonView(View.View1.class)

    @GetMapping("/sellers")

    public List<User> getSeller(){
        return sellerRepo.findAll();
    }
    @GetMapping("/seller")
    @JsonView(View.View1.class)



    public Optional<Seller> getSellerById(@RequestParam Long id){
        boolean exist=sellerRepo.existsById(id);
        if(!exist){
            throw new RuntimeException("user doesn't exisits");
        }

        return sellerRepo.findUserById(id);
    }
    @GetMapping("/")
    public List showPurchase(){
        return purchaseServices.showPurchase();
    }
    @GetMapping("/orders")
    public List<CustomerProductDTO> getSellerOrders(@RequestParam Long id){return sellerSrvices.getSellerOrdersBId(id);}


    @GetMapping("/product")
    @JsonView(View.View2.class)
    public List<Product> getProducts(@RequestParam Long id){

          return productRepo.showProductWithSpecificSeller(id);

    }
    @GetMapping("/{id}/seller-comments")

    public List<CommentFromDto> showSellerComments(@PathVariable Long id) {
        return sellerSrvices.showSellerComment(id);
    }
    @GetMapping("/{id}/purchases-by-date")
    public List showPurchaseByDay(@PathVariable Long id, @RequestParam Integer day, @RequestParam Integer month, @RequestParam Integer year){
        return purchaseServices.showPurchaseByDay(id,day,month,year);
    }
    @GetMapping("/{id}/purchases-by-monthOfYear")
    public List showPurchaseByMonth(@PathVariable  Long id,@RequestParam Integer month,@RequestParam Integer year){
        return purchaseServices.showPurchaseByMonth(id,month,year);
    }
    @GetMapping("/{id}/purchases-by-year")
    public List showPurchaseByYear(@PathVariable  Long id, @RequestParam Integer year){
        return purchaseServices.showPurchaseByYear(id,year);
    }

    @DeleteMapping("comments/{id}")
    public void deleteComment(@PathVariable Long id){
        sellerCommentRepo.deleteById(id);
    }
    @GetMapping("/{id}/purchases")
    public List showPurchases(@PathVariable  Long id){
        return purchaseServices.showPurchases(id);
    }
    @GetMapping("/{id}/purchases-price-by-date")
    public AtomicReference<Double> purchasesPriceByDay(@PathVariable  Long id, @RequestParam Integer day, @RequestParam Integer month, @RequestParam Integer year){
        return purchaseServices.pricePurchaseByDay(id,day,month,year);
    }
    @GetMapping("/{id}/purchases-price-by-monthOfYear")
    public AtomicReference<Double> purchasesPriceByMonth(@PathVariable  Long id, @RequestParam Integer month,@RequestParam Integer year){
        return purchaseServices.pricePurchaseByMonth(id,month,year);
    }
    @GetMapping("/{id}/purchases-price-by-year")
    public AtomicReference<Double> purchasesPriceByYear(@PathVariable  Long id, @RequestParam Integer year){
        return purchaseServices.pricePurchaseByYear(id,year);
    }

    @GetMapping("/{id}/purchases-price")
    public AtomicReference<Double> purchasesPrice(@PathVariable  Long id){
        return purchaseServices.pricePurchases(id);
    }

    @GetMapping("/purchases-by-date")
    public List showPurchaseByDay( @RequestParam Integer day,@RequestParam Integer month,@RequestParam Integer year){
        return purchaseServices.showPurchasesByDay(day,month,year);
    }
    @GetMapping("/purchases-by-monthOfYear")
    public List showPurchaseByMonth(@RequestParam Integer month,@RequestParam Integer year){
        return purchaseServices.showPurchasesByMonth(month,year);
    }
    @GetMapping("/purchases-by-year")
    public List showPurchaseByYear( @RequestParam Integer year){
        return purchaseServices.showPurchasesByYear(year);
    }


    @GetMapping("/purchases-price-by-date")
    public AtomicReference<Double> purchasesPriceByDay( @RequestParam Integer day, @RequestParam Integer month,@RequestParam Integer year){
        return purchaseServices.pricePurchasesByDay(day,month,year);
    }
    @GetMapping("/purchases-price-by-monthOfYear")
    public AtomicReference<Double> purchasesPriceByMonth( @RequestParam Integer month,@RequestParam Integer year){
        return purchaseServices.pricePurchasesByMonth(month,year);
    }
    @GetMapping("/purchases-price-by-year")
    public AtomicReference<Double> purchasesPriceByYear( @RequestParam Integer year){
        return purchaseServices.pricePurchasesByYear(year);
    }

    @GetMapping("/purchases-price")
    public AtomicReference<Double> purchasesPrice(){
        return purchaseServices.pricePurchasess();
    }
}
