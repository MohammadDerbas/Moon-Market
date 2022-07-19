package com.example.demo.repo;

import com.example.demo.entity.Purchase;
import com.example.demo.entity.PurchaseId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepo extends JpaRepository<Purchase, PurchaseId> {
}
