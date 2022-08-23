package com.example.demo.services;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Prize;
import com.example.demo.entity.PrizeChanger;
import com.example.demo.entity.PrizeChangerId;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repo.CustomerRepo;
import com.example.demo.repo.PrizeChangerRepo;
import com.example.demo.repo.PrizeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void exchangePrize(String email,Integer prize){
        boolean exist=customerRepo.existsByEmail(email);
        if(!exist){
            throw new ApiRequestException("user not found");
        }
        Customer customer= (Customer) customerRepo.findUserByEmail(email).get();
        Prize prize1=prizeRepo.findById(prize.longValue()).get();
        if(prize1.getPoints()>customer.getPoints()){
            throw new ApiRequestException("can't exchange this prize you need more points");
        }
        PrizeChanger prizeChanger=new PrizeChanger(new PrizeChangerId(prize.longValue(),customer.getId()),prize1,customer);
        customerRepo.updatePoints(customer.getPoints()-prize1.getPoints(),customer);
        prizeChangerRepo.save(prizeChanger);
    }

    public List<Prize> showPrizes() {
        return prizeRepo.findAll();
    }

    public List<Prize> whatCanIExchange(String email) {
        boolean exist=customerRepo.existsByEmail(email);
        if(!exist){
            throw new ApiRequestException("user not found");
        }
        Customer customer= (Customer) customerRepo.findUserByEmail(email).get();
        return prizeRepo.findListByPoints(customer.getPoints());
    }

    public List<Prize> customerPrizes(String email) {
        boolean exist=customerRepo.existsByEmail(email);
        if(!exist){
            throw new ApiRequestException("user not found");
        }
        Customer customer= (Customer) customerRepo.findUserByEmail(email).get();
        return prizeChangerRepo.customerPrize(customer);
    }
}
