package com.example.demo.repo;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LikeRepo extends JpaRepository<Like, LikeId> {
    @Query("SELECT c.product from ProductLike c where c.customer.id=?1")
    List<Product> findProductByCustomerId(Long id);
    @Query("SELECT c.customer from ProductLike c where c.product.id=?1")
    List<Customer> findCustomerByProductId(Long id);

    @Transactional
    @Modifying
    @Query("delete from ProductLike f where f.id = ?1")
    void deleteById(FollowersId followersId);
    @Query("SELECT (count(c)>0) from ProductLike c where c.customer.id=?1 and c.product.id=?2")
    Boolean isCustmoerLikeProduct(Long id,Long id2);
}
