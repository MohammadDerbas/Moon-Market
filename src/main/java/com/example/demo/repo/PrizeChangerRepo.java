package com.example.demo.repo;

import com.example.demo.entity.PrizeChanger;
import com.example.demo.entity.PrizeChangerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrizeChangerRepo extends JpaRepository<PrizeChanger, PrizeChangerId> {
}
