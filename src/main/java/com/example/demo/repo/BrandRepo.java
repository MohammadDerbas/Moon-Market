package com.example.demo.repo;

import com.example.demo.entity.Brand;
import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepo extends JpaRepository<Brand,Long> {
    @Query("select (count(b) > 0) from Brand b where b.brand = ?1")
    boolean existsByBrand (String brand);
    @Query("select b from Brand b where b.brand = ?1")
    Brand findByBrand(String brand);

    @Query("select b.products from Brand b where b.brand = ?1")
    List<Product> findProductByBrand(String brand);
}
