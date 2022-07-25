package com.example.demo.services;

import com.example.demo.entity.*;
import com.example.demo.repo.FollowRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServices {
    private final FollowRepo followRepo;
    private final UserRepo userRepo;
    @Autowired
    public FollowServices(FollowRepo followRepo, UserRepo userRepo) {
        this.followRepo = followRepo;
        this.userRepo = userRepo;
    }

    public List<Seller> showfollwing(Long id) {
        return followRepo.findSellerByCustomerId(id);
    }


}
