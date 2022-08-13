package com.example.demo.services;

import com.example.demo.DTO.CustomerProductPurchaseDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;
import com.example.demo.entity.Purchase;
import com.example.demo.repo.CustomerRepo;
import com.example.demo.repo.ProductRepo;
import com.example.demo.repo.PurchaseRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class PurchaseServices {
    private final PurchaseRepo purchaseRepo;
    private final ProductRepo productRepo;
    private final CustomerRepo customerRepo;


    public PurchaseServices(PurchaseRepo purchaseRepo, ProductRepo productRepo, CustomerRepo customerRepo) {
        this.purchaseRepo = purchaseRepo;
        this.productRepo = productRepo;
        this.customerRepo = customerRepo;
    }
    public List showPurchase(){
        return purchaseRepo.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public List showCustomerPurchases(Long id) {
    return purchaseRepo.findAllPurchasesByCustomerId(id).stream().map(this::convertToDto).collect(Collectors.toList());
    }
    private CustomerProductPurchaseDTO convertToDto(Purchase purchase){
        Optional<Customer> customer=customerRepo.findUserByID(purchase.getCustomerId());
        Product product= productRepo.findProductById(purchase.getProductId());
        CustomerProductPurchaseDTO customerProductPurchaseDTO=new CustomerProductPurchaseDTO();
        customerProductPurchaseDTO.setName(customer.get().getFirstName()+" "+customer.get().getLastName());
        customerProductPurchaseDTO.setType(product.getType().getType());
        customerProductPurchaseDTO.setDate(purchase.getDate());
        customerProductPurchaseDTO.setQuantity(purchase.getQuantity());
        customerProductPurchaseDTO.setPrice(purchase.getPrice());
        customerProductPurchaseDTO.setDescription(product.getDescription());
        customerProductPurchaseDTO.setSize(product.getSizes());
        customerProductPurchaseDTO.setBrand(product.getBrand());
        return customerProductPurchaseDTO;

    }
    public List showPurchaseByDay(Long id,Integer day,Integer month,Integer year){
        return purchaseRepo.findAllPurchasesByCustomerIdAndByDay(id,day,month,year).stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public List showPurchaseByMonth(Long id,Integer month,Integer year){
        return purchaseRepo.findAllPurchasesByCustomerIdAndByMonth(id,month,year).stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public List showPurchaseByYear(Long id,Integer year){
        return purchaseRepo.findAllPurchasesByCustomerIdAndByYear(id,year).stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public AtomicReference<Double> pricePurchaseByDay(Long id, Integer day, Integer month,Integer year){
        AtomicReference<Double> sum= new AtomicReference<>(0.0);
        List<CustomerProductPurchaseDTO> list=showPurchaseByDay(id,day,month,year);
        list.stream().forEach(customerProductPurchaseDTO -> sum.updateAndGet(v -> v + customerProductPurchaseDTO.getPrice()));
        return sum;
    }
    public AtomicReference<Double> pricePurchaseByMonth(Long id, Integer month,Integer year){
        AtomicReference<Double> sum= new AtomicReference<>(0.0);
        List<CustomerProductPurchaseDTO> list=showPurchaseByMonth(id,month,year);
        list.stream().forEach(customerProductPurchaseDTO -> sum.updateAndGet(v -> v + customerProductPurchaseDTO.getPrice()));
        return sum;
    }
    public AtomicReference<Double> pricePurchaseByYear(Long id, Integer year){
        AtomicReference<Double> sum= new AtomicReference<>(0.0);
        List<CustomerProductPurchaseDTO> list=showPurchaseByYear(id,year);
        list.stream().forEach(customerProductPurchaseDTO -> sum.updateAndGet(v -> v + customerProductPurchaseDTO.getPrice()));
        return sum;
    }

    public List showPurchases(Long id) {
        return purchaseRepo.findAllPurchasesByCustomerId(id).stream().map(this::convertToDto).collect(Collectors.toList());

    }

    public AtomicReference<Double> pricePurchases(Long id) {
        AtomicReference<Double> sum= new AtomicReference<>(0.0);
        List<CustomerProductPurchaseDTO> list=showPurchases(id);
        list.stream().forEach(customerProductPurchaseDTO -> sum.updateAndGet(v -> v + customerProductPurchaseDTO.getPrice()));
        return sum;
    }

    public List showPurchasesByDay(Integer day, Integer month, Integer year) {
        return purchaseRepo.findAllPurchasesByDay(day,month,year).stream().map(this::convertToDto).collect(Collectors.toList());

    }

    public List showPurchasesByMonth(Integer month, Integer year) {
        return purchaseRepo.findAllPurchasesByMonth(month,year).stream().map(this::convertToDto).collect(Collectors.toList());

    }

    public List showPurchasesByYear(Integer year) {
        return purchaseRepo.findAllPurchasesByYear(year).stream().map(this::convertToDto).collect(Collectors.toList());

    }

    public AtomicReference<Double> pricePurchasesByDay(Integer day, Integer month, Integer year) {
        AtomicReference<Double> sum= new AtomicReference<>(0.0);
        List<CustomerProductPurchaseDTO> list=showPurchasesByDay(day,month,year);
        list.stream().forEach(customerProductPurchaseDTO -> sum.updateAndGet(v -> v + customerProductPurchaseDTO.getPrice()));
        return sum;
    }

    public AtomicReference<Double> pricePurchasesByMonth(Integer month, Integer year) {
        AtomicReference<Double> sum= new AtomicReference<>(0.0);
        List<CustomerProductPurchaseDTO> list=showPurchasesByMonth(month,year);
        list.stream().forEach(customerProductPurchaseDTO -> sum.updateAndGet(v -> v + customerProductPurchaseDTO.getPrice()));
        return sum;
    }

    public AtomicReference<Double> pricePurchasesByYear(Integer year) {
        AtomicReference<Double> sum= new AtomicReference<>(0.0);
        List<CustomerProductPurchaseDTO> list=showPurchasesByYear(year);
        list.stream().forEach(customerProductPurchaseDTO -> sum.updateAndGet(v -> v + customerProductPurchaseDTO.getPrice()));
        return sum;
    }

    public AtomicReference<Double> pricePurchasess() {
        AtomicReference<Double> sum= new AtomicReference<>(0.0);
        List<CustomerProductPurchaseDTO> list=showPurchase();
        list.stream().forEach(customerProductPurchaseDTO -> sum.updateAndGet(v -> v + customerProductPurchaseDTO.getPrice()));
        return sum;
    }
}
