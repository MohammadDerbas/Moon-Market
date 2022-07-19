package com.example.demo.repo;

import com.example.demo.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SizeRepo extends JpaRepository<Size,Long> {
    @Query("select (count(s) > 0) from Size s where s.size = ?1")
    boolean existsBySize(String size);

    @Query("select s from Size s where s.size = ?1")
    Size findBySize(String size);
}
