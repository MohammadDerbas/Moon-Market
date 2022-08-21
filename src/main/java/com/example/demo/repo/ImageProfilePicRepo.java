package com.example.demo.repo;

import com.example.demo.entity.Img;
import com.example.demo.entity.ImgProfilePic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ImageProfilePicRepo extends JpaRepository<ImgProfilePic ,Long> {
    @Query("select i from ImgProfilePic i where i.name = ?1")
    Optional<ImgProfilePic> findByName(String name);

    @Transactional
    @Modifying
    @Query("delete  from ImgProfilePic s where s=?1")
    void deleteByImages(ImgProfilePic images);

    @Query("SELECT (count (s)>0) from ImgProfilePic s where s.name=?1")
    Boolean existsByImages(String images);
}
