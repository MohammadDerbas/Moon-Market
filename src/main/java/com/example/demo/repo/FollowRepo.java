package com.example.demo.repo;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Followers;
import com.example.demo.entity.FollowersId;
import com.example.demo.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FollowRepo extends JpaRepository<Followers,Long> {
    @Query("SELECT c.seller from Followers c where c.customer.id=?1")
    List<Seller> findSellerByCustomerId(Long id);
    @Query("SELECT c.customer from Followers c where c.seller.id=?1")
    List<Customer> findCustomerBySellerId(Long id);

    @Transactional
    @Modifying
    @Query("delete from Followers f where f.id = ?1")
    void deleteById(FollowersId followersId);
}
