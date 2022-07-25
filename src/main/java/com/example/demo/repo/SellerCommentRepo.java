package com.example.demo.repo;

import com.example.demo.entity.SellerComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerCommentRepo extends JpaRepository<SellerComment,Long> {
    @Query("SELECT c.comment FROM SellerComment c where c.seller.id=?1")
    List findAllSellerCommentBySellerId(Long id);

    @Query("select s from SellerComment s where s.seller.id=?1")
    List<SellerComment> findAll(Long id);
}
