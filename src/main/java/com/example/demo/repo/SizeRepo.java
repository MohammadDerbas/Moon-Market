package com.example.demo.repo;

import com.example.demo.entity.Product;
import com.example.demo.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SizeRepo extends JpaRepository<Size,Long> {
    @Query("select (count(s) > 0) from Size s where s.size in ?1")
    boolean existsBySizeIn(List<String>sizes);

    @Query("select s from Size s where s.size in ?1")
    List<Size> findBySize(List<String>sizes);
    @Query("select s.products from Size s where s.size in ?1")
    List<Product> findProductsBySizeIn(List<String>sizes);
}
