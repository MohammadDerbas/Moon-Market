package com.example.demo.repo;

import com.example.demo.entity.Customer;
import com.example.demo.entity.MemberShip;
import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberShipRepo extends JpaRepository<MemberShip,Long> {
    @Query("select m from MemberShip m where m.type = ?1")
    MemberShip findByType(String type);

    @Query("select m.customers from MemberShip m where m.type=?1")
    List<Customer> findCustomerByMemberShipType(String membership);

    @Query("select (count(m) > 0) from MemberShip m where m.type = ?1")
    boolean existsByMemberShipType(String membership);
}
