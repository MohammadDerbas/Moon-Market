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

    @Query("select (count(s) > 0) from SellerComment s where s.id = ?1 and s.customer.email=?2")
    boolean existsById(Long id,String email);

    @Query("SELECT AVG(e.rating) FROM SellerComment e WHERE e.seller.id = ?1")
    Integer getRatingAvg(Long id);
}


