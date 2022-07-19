package com.example.demo.repo;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderId;
import com.example.demo.entity.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository <Order,Long> {

    @Query("select c.product from Cart c where c.customer.id = ?1")
    List<Product> findAlLProductByCustomer_Id(Long id);

    @Query("select distinct c.customer from Cart c")
    List<Customer>  findAllCustomer();


    @Transactional
    @Query("delete from Cart c where c.id = ?1")
    @Modifying
    void deleteById(OrderId orderId);
}
