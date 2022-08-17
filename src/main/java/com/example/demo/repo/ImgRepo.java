package com.example.demo.repo;

import com.example.demo.entity.Img;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgRepo extends JpaRepository<Img,Long> {

}
