package com.example.demo.repo;

import com.example.demo.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepo extends JpaRepository<Brand,Long> {
    @Query("select (count(b) > 0) from Brand b where b.brand = ?1")
    boolean existsByBrand (String brand);
    @Query("select b from Brand b where b.brand = ?1")
    Brand findByBrand(String brand);
}
