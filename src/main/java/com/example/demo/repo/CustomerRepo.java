package com.example.demo.repo;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends UserRepo {
    @Query("SELECT s FROM Customer s where s.id=?1")
    Optional<Customer> findUserByID(Long id);

    @Query("select (count(u) > 0) from Customer u where u.id = ?1")
    @Override
    boolean existsById(Long id);

}
