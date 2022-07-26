package com.example.demo.repo;

import com.example.demo.entity.Product;
import com.example.demo.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepo extends JpaRepository<Type,Long> {
    @Query("select (count(t) > 0) from Type t where t.type = ?1")
    boolean existsByType(String type);
    @Query("select t from Type t where t.type = ?1")
    Type findByType(String type);
    @Query("select t.products from Type t where t.type=?1")
    List<Product> findProductsByType(String type);
}
