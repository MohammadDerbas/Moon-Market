package com.example.demo.repo;

import com.example.demo.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface PurchaseRepo extends JpaRepository<Purchase, Long> {
    @Query("select p from Purchase p where p.customerId=?1")
    List<Purchase> findAllPurchasesByCustomerId(Long id);
    @Query(value = "select * from purchase  WHERE purchase.customer_id=?1 and extract(Day from purchase.date)=?2 and extract(MONTH from purchase.date)=?3 and extract(YEAR from purchase.date)=?4",nativeQuery = true)
    List<Purchase> findAllPurchasesByCustomerIdAndByDay(Long id,Integer day1,Integer month,Integer year);
    @Query(value = "select * from purchase  WHERE purchase.customer_id=?1 and extract(Month from purchase.date)=?2 and extract(YEAR from purchase.date)=?3 ",nativeQuery = true)
    List<Purchase> findAllPurchasesByCustomerIdAndByMonth(Long id,Integer month,Integer year);
    @Query(value = "select * from purchase  WHERE purchase.customer_id=?1 and extract(YEAR from purchase.date)=?2 ",nativeQuery = true)
    List<Purchase> findAllPurchasesByCustomerIdAndByYear(Long id,Integer year);

    @Query(value = "select * from purchase  WHERE  extract(Day from purchase.date)=?1 and extract(MONTH from purchase.date)=?2 and extract(YEAR from purchase.date)=?3",nativeQuery = true)
    List<Purchase> findAllPurchasesByDay(Integer day1,Integer month,Integer year);
    @Query(value = "select * from purchase  WHERE  extract(Month from purchase.date)=?1 and extract(YEAR from purchase.date)=?2 ",nativeQuery = true)
    List<Purchase> findAllPurchasesByMonth(Integer month,Integer year);
    @Query(value = "select * from purchase  WHERE  extract(YEAR from purchase.date)=?1 ",nativeQuery = true)
    List<Purchase> findAllPurchasesByYear(Integer year);

}
