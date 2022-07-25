package com.example.demo.repo;

import com.example.demo.entity.MemberShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberShipRepo extends JpaRepository<MemberShip,Long> {
    @Query("select m from MemberShip m where m.type = ?1")
    MemberShip findByType(String type);
}
