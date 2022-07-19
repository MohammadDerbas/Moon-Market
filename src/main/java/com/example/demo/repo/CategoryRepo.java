package com.example.demo.repo;

import com.example.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Id;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    @Query("select (count(c) > 0) from Category c where c.category = ?1")
    boolean existsByCategory(String category);
    @Query("select c from Category c where c.category = ?1")
    Category findByCategory(String category);

}
