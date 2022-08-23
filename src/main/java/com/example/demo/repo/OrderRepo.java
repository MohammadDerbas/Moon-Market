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

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepo extends JpaRepository <Order,Long> {

    @Query("select c.product from Cart c where c.customer.id = ?1")
    List<Product> findAlLProductByCustomer_Id(Long id);
    @Query("select c from Cart c where c.customer.id = ?1")
    List<Order> findAlLOrdersByCustomer_Id(Long id);
    @Query("select c from Cart  c where  c.product.id IN ?1")
    List<Order> getSellerOrdersByProductIds(List<Long> productIds);

    @Query("select distinct c.customer from Cart c")
    List<Customer>  findAllCustomer();
    @Query("select c  from Cart c where c.reference=?1")
    Order  getOrderByReference(UUID ref);
    @Query("select COUNT(c) from Cart c where c.product.id IN ?1 and c.status='PENDING' ")
    Integer getPendingOrdersCount(List<Long> ids);


    @Transactional
    @Query("delete from Cart c where c.id = ?1")
    @Modifying
    void deleteById(OrderId orderId);
    @Query("select c.price from Cart c")
    Double price();
    @Query("select c.customer.id from Cart c where c.customer.email=?1")
    Long getIdByEmail(String email);
    @Query("select c.quantity from Cart c where c.id=?1")
    Integer quantity(OrderId id);
    @Transactional
    @Query("delete from Cart c where c.customer.id = ?1")
    @Modifying
    void deleteAllByCustomerId(Long id);
    @Query ("select count(c)>0 from Cart c where c.id=?1")
    Boolean isOrderExist(OrderId orderId);
    @Transactional
    @Modifying
    @Query("update Cart c set c.quantity=?1 where c.id=?2")
    void updateQuantity(Integer quantity,OrderId orderId);

}
