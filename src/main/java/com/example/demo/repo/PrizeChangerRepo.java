package com.example.demo.repo;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Prize;
import com.example.demo.entity.PrizeChanger;
import com.example.demo.entity.PrizeChangerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrizeChangerRepo extends JpaRepository<PrizeChanger, PrizeChangerId> {
    @Query("select p.prize from PrizeChanger p where p.customer=?1")
    List<Prize> customerPrize(Customer customer);
}
