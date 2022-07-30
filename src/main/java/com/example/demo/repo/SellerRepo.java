package com.example.demo.repo;

import com.example.demo.entity.Seller;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepo extends UserRepo{
   @Query("SELECT s FROM Seller s where s.id=?1")
    Optional<Seller> findUserById(Long id);

    @Query("select (count(u) > 0) from Seller u where u.id = ?1")
    @Override
    boolean existsById(Long aLong);

    @Query("select u from Seller u")
    @Override
    List<User> findAll();
 @Query("select u.id from Seller u where u.email=?1")
  Long findIdByEmail(String email);

}
