package com.example.demo.repo;

import com.example.demo.entity.ColorProps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ColorPropsRepo extends JpaRepository<ColorProps,Long> {
    @Query("select (count(s)>0) from ColorProps s where s.color=?1 and s.product.id=?2")
    Boolean existsByColor(String color,Long id);
    @Query("SELECT s from ColorProps s where s.color=?1 and s.product.id=?2")
    ColorProps findByColor(String color,Long id);
    @Modifying
    @Transactional
    @Query("delete from ColorProps s where s.color=?1")
    void deleteByColor(String color);
}
