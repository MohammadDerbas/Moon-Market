package com.example.demo.repo;

import com.example.demo.entity.Prize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrizeRepo extends JpaRepository<Prize,Long> {
    @Query("select p from Prize p where p.points<=?1")
    List<Prize> findListByPoints(Integer points);
}
