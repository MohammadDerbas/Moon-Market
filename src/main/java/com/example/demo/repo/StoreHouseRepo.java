package com.example.demo.repo;

import com.example.demo.entity.StoreHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreHouseRepo extends JpaRepository <StoreHouse,Long> {
    @Query("select c from StoreHouse c where c.product.id=?1")
    StoreHouse findStoreHouseByProductId(Long id);
}
