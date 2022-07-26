package com.example.demo.services;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repo.MemberShipRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberShipServices {
    private final MemberShipRepo memberShipRepo;

    public MemberShipServices(MemberShipRepo memberShipRepo) {
        this.memberShipRepo = memberShipRepo;
    }
    public List<Customer> findCustomersByMembership(String membership){
        boolean exists=memberShipRepo.existsByMemberShipType(membership);
        if(!exists){
            throw new ApiRequestException("MemberShip with type"+membership+" does not exists");
        }
        return memberShipRepo.findCustomerByMemberShipType(membership);
    }
}
