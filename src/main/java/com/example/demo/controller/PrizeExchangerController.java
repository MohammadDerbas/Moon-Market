package com.example.demo.controller;

import com.example.demo.services.PrizeExchangerServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exchange")
public class PrizeExchangerController {
    private final PrizeExchangerServices prizeExchangerServices ;

    public PrizeExchangerController(PrizeExchangerServices prizeExchangerServices) {
        this.prizeExchangerServices = prizeExchangerServices;
    }
    @GetMapping
    public void exchangePrize(){
        prizeExchangerServices.exchangePrize();
    }
}
