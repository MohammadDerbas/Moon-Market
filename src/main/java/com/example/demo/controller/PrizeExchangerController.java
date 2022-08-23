package com.example.demo.controller;

import com.example.demo.entity.Prize;
import com.example.demo.services.PrizeExchangerServices;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("prize/customer")
public class PrizeExchangerController {
    private final PrizeExchangerServices prizeExchangerServices ;

    public PrizeExchangerController(PrizeExchangerServices prizeExchangerServices) {
        this.prizeExchangerServices = prizeExchangerServices;
    }
    @PreAuthorize("hasAuthority('CUSTOMER')and #id==authentication.principal.id")

    @GetMapping("/{id}/exchange")
    public void exchangePrize(@PathVariable long id, Principal principal, @RequestParam Integer prize){
        prizeExchangerServices.exchangePrize(principal.getName(),prize);
    }
    @PreAuthorize("hasAuthority('CUSTOMER')and #id==authentication.principal.id")

    @GetMapping("/{id}/show")
    public List<Prize> showPrizes(@PathVariable long id){
        return prizeExchangerServices.showPrizes();
    }
    @PreAuthorize("hasAuthority('CUSTOMER')and #id==authentication.principal.id")

    @GetMapping("/{id}/show_prize_can_exchange")
    public List<Prize>whatCanIExhange(@PathVariable long id,Principal principal){
        return prizeExchangerServices.whatCanIExchange(principal.getName());
    }
    @PreAuthorize("hasAuthority('CUSTOMER')and #id==authentication.principal.id")

    @GetMapping("/{id}/my_prizes")
    public List<Prize>customerPrizes(@PathVariable long id,Principal principal){
        return prizeExchangerServices.customerPrizes(principal.getName());
    }

}
