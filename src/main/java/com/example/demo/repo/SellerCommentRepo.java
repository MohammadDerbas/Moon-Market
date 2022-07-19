package com.example.demo.repo;

import com.example.demo.entity.SellerComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerCommentRepo extends JpaRepository<SellerComment,Long> {
}
