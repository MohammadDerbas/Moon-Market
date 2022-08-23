package com.example.demo.repo;

import com.example.demo.entity.Prize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrizeRepo extends JpaRepository<Prize,Long> {
}
