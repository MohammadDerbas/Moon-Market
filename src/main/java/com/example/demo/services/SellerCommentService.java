package com.example.demo.services;

import com.example.demo.repo.SellerCommentRepo;
import org.springframework.stereotype.Service;

@Service
public class SellerCommentService {
    private final SellerCommentRepo sellerCommentRepo;

    public SellerCommentService(SellerCommentRepo sellerCommentRepo) {
        this.sellerCommentRepo = sellerCommentRepo;
    }
}
