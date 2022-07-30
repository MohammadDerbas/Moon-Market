package com.example.demo.controller;

import com.example.demo.services.PurchaseServices;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("purchase")
public class PurchaseController {
private final PurchaseServices purchaseServices;

    public PurchaseController(PurchaseServices purchaseServices) {
        this.purchaseServices = purchaseServices;
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/")
    public List showPurchase(){
        return purchaseServices.showPurchase();
    }
    @GetMapping("/{id}/purchases-by-date")
    public List showPurchaseByDay(@PathVariable  Long id, @RequestParam Integer day,@RequestParam Integer month,@RequestParam Integer year){
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
    @GetMapping("/{id}/purchases-price-by-date")
    public AtomicReference<Double> purchasesPriceByDay(@PathVariable  Long id, @RequestParam Integer day, @RequestParam Integer month,@RequestParam Integer year){
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
}
