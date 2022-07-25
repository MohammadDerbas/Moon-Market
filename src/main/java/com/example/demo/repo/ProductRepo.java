package com.example.demo.repo;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    @Query("SELECT s FROM Product s where s.id in (SELECT m.id.productId from StoreHouse m where m.id.sellerId=?1)")
    List<Product> showProductWithSpecificSeller( long id);
    @Query("select p from Product p where p.id = ?1")
    Product findProductById(long id);
    @Query("select (count(u) > 0) from Product u where u.id = ?1")
    @Override
    boolean existsById(Long aLong);

}
