package com.example.demo.repo;

import com.example.demo.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCommentRepo extends JpaRepository <ProductComment,Long>  {
}
