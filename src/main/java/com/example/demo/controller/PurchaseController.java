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
    @PreAuthorize("hasAuthority('CUSTOMER')and #id==authentication.principal.id")
    @GetMapping("/{id}/purchases-by-date")
    public List showPurchaseByDay(@PathVariable  Long id, @RequestParam Integer day,@RequestParam Integer month,@RequestParam Integer year){
        return purchaseServices.showPurchaseByDay(id,day,month,year);
    }
    @PreAuthorize("hasAuthority('CUSTOMER')and #id==authentication.principal.id")
    @GetMapping("/{id}/purchases-by-monthOfYear")
    public List showPurchaseByMonth(@PathVariable  Long id,@RequestParam Integer month,@RequestParam Integer year){
        return purchaseServices.showPurchaseByMonth(id,month,year);
    }
    @PreAuthorize("hasAuthority('CUSTOMER')and #id==authentication.principal.id")
    @GetMapping("/{id}/purchases-by-year")
    public List showPurchaseByYear(@PathVariable  Long id, @RequestParam Integer year){
        return purchaseServices.showPurchaseByYear(id,year);
    }
    @PreAuthorize("hasAuthority('CUSTOMER')and #id==authentication.principal.id")
    @GetMapping("/{id}/purchases")
    public List showPurchases(@PathVariable  Long id){
        return purchaseServices.showPurchases(id);
    }
    @PreAuthorize("hasAuthority('CUSTOMER')and #id==authentication.principal.id")
    @GetMapping("/{id}/purchases-price-by-date")
    public AtomicReference<Double> purchasesPriceByDay(@PathVariable  Long id, @RequestParam Integer day, @RequestParam Integer month,@RequestParam Integer year){
        return purchaseServices.pricePurchaseByDay(id,day,month,year);
    }
    @PreAuthorize("hasAuthority('CUSTOMER')and #id==authentication.principal.id")
    @GetMapping("/{id}/purchases-price-by-monthOfYear")
    public AtomicReference<Double> purchasesPriceByMonth(@PathVariable  Long id, @RequestParam Integer month,@RequestParam Integer year){
        return purchaseServices.pricePurchaseByMonth(id,month,year);
    }
    @PreAuthorize("hasAuthority('CUSTOMER')and #id==authentication.principal.id")
    @GetMapping("/{id}/purchases-price-by-year")
    public AtomicReference<Double> purchasesPriceByYear(@PathVariable  Long id, @RequestParam Integer year){
        return purchaseServices.pricePurchaseByYear(id,year);
    }
    @PreAuthorize("hasAuthority('CUSTOMER')and #id==authentication.principal.id")

    @GetMapping("/{id}/purchases-price")
    public AtomicReference<Double> purchasesPrice(@PathVariable  Long id){
        return purchaseServices.pricePurchases(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/purchases-by-date")
    public List showPurchaseByDay( @RequestParam Integer day,@RequestParam Integer month,@RequestParam Integer year){
        return purchaseServices.showPurchasesByDay(day,month,year);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/purchases-by-monthOfYear")
    public List showPurchaseByMonth(@RequestParam Integer month,@RequestParam Integer year){
        return purchaseServices.showPurchasesByMonth(month,year);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/purchases-by-year")
    public List showPurchaseByYear( @RequestParam Integer year){
        return purchaseServices.showPurchasesByYear(year);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/purchases-price-by-date")
    public AtomicReference<Double> purchasesPriceByDay( @RequestParam Integer day, @RequestParam Integer month,@RequestParam Integer year){
        return purchaseServices.pricePurchasesByDay(day,month,year);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/purchases-price-by-monthOfYear")
    public AtomicReference<Double> purchasesPriceByMonth( @RequestParam Integer month,@RequestParam Integer year){
        return purchaseServices.pricePurchasesByMonth(month,year);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/purchases-price-by-year")
    public AtomicReference<Double> purchasesPriceByYear( @RequestParam Integer year){
        return purchaseServices.pricePurchasesByYear(year);
    }
    @PreAuthorize("hasAuthority('ADMIN')")

    @GetMapping("/purchases-price")
    public AtomicReference<Double> purchasesPrice(){
        return purchaseServices.pricePurchasess();
    }
}
