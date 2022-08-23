package com.example.demo.services;

import com.example.demo.repo.CustomerRepo;
import com.example.demo.repo.PrizeChangerRepo;
import com.example.demo.repo.PrizeRepo;
import org.springframework.stereotype.Service;

@Service
public class PrizeExchangerServices {
    private final PrizeRepo prizeRepo;
    private final PrizeChangerRepo prizeChangerRepo;
    private final CustomerRepo customerRepo;

    public PrizeExchangerServices(PrizeRepo prizeRepo, PrizeChangerRepo prizeChangerRepo, CustomerRepo customerRepo) {
        this.prizeRepo = prizeRepo;
        this.prizeChangerRepo = prizeChangerRepo;
        this.customerRepo = customerRepo;
    }
    public void exchangePrize(){

    }
}
