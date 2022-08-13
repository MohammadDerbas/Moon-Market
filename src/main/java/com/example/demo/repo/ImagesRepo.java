package com.example.demo.repo;

import com.example.demo.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ImagesRepo extends JpaRepository<Images,Long> {
    @Query("SELECT (count (s)>0) from Images s where s.images=?1")
    Boolean existsByImages(String images);
    @Query("SELECT s FROM Images s where s.images=?1")
    Images findByImages(String images);
    @Transactional
    @Modifying
    @Query("delete  from Images s where s=?1")
    void deleteByImages(Images images);

 

}
