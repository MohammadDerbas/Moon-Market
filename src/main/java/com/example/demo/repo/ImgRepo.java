package com.example.demo.repo;

import com.example.demo.entity.Img;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ImgRepo extends JpaRepository<Img,Long> {

    @Query("select i from Img i where i.name = ?1")
    Optional<Img> findByName(String name);

    @Transactional
    @Modifying
    @Query("delete  from Img s where s=?1")
    void deleteByImages(Img images);

    @Query("SELECT (count (s)>0) from Img s where s.name=?1")
    Boolean existsByImages(String images);
}
