//package com.example.demo.paypal;
//
//import com.example.demo.DTO.CustomerProductDTO;
//import com.example.demo.exception.ApiRequestException;
//import com.example.demo.services.OrderServices;
//import com.example.demo.services.ProductServices;
//import com.paypal.api.payments.Links;
//import com.paypal.api.payments.Payment;
//import com.paypal.base.rest.PayPalRESTException;
//import org.hibernate.annotations.Synchronize;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//import java.util.concurrent.atomic.AtomicReference;
//
//@Controller
//public class PaypalController {
//    private final PaypalService service;
//    public static final String SUCCESS_URL = "pay/success";
//    public static final String CANCEL_URL = "pay/cancel";
//    private final ProductServices productServices;
//    private final OrderServices orderServices;
//    @Autowired
//    public PaypalController(PaypalService service, ProductServices productServices, OrderServices orderServices) {
//        this.service = service;
//        this.productServices = productServices;
//        this.orderServices = orderServices;
//    }
//
//    @GetMapping("/")
//    public String home() {
//        return "home";
//    }
//
//    @GetMapping ("/pay")
//    public String payment() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String email=auth.getName();
//        Long id=orderServices.getIdByEmail(email);
//
//        Double sum=orderServices.price(id);
//        System.out.println(sum);
//
//        try {
//            Payment payment = service.createPayment(sum, "USD", "paypal",
//                    "sale", "testing", "http://localhost:8080/" + CANCEL_URL,
//                    "http://localhost:8080/" + SUCCESS_URL);
//            for(Links link:payment.getLinks()) {
//                if(link.getRel().equals("approval_url")) {
//
//                    return "redirect:"+link.getHref();
//                }
//            }
//
//        } catch (PayPalRESTException e) {
//
//            e.printStackTrace();
//        }
//        return "redirect:/";
//    }
//
//    @GetMapping(value = CANCEL_URL)
//    public String cancelPay() {
//        return "cancel";
//    }
//
//    @GetMapping(value = SUCCESS_URL)
//
//    public  String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String email=auth.getName();
//        Long id=orderServices.getIdByEmail(email);
//        try {
//            Payment payment = service.executePayment(paymentId, payerId);
//            System.out.println(payment.toJSON());
//            if (payment.getState().equals("approved")) {
//                orderServices.updateQuantityProductByCustomerId(id);
//                orderServices.buyOrders(id);
//                orderServices.deleteAllByCustomerId(id);
//                return "success";
//            }
//        } catch (PayPalRESTException e) {
//            System.out.println(e.getMessage());
//        }
//        return "redirect:/";
//    }
//
//}
//
