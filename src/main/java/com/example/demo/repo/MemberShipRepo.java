package com.example.demo.repo;

import com.example.demo.entity.MemberShip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberShipRepo extends JpaRepository<MemberShip,Long> {
}
