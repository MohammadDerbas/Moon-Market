package com.example.demo.repo;

import com.example.demo.entity.ProductComment;
import com.example.demo.entity.SellerComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCommentRepo extends JpaRepository <ProductComment,Long>  {
    @Query("SELECT c.comment FROM ProductComment c where c.product.id=?1")
    List<ProductComment> findAllProductCommentByProductId(Long id);
}
