package com.example.demo.repo;

import com.example.demo.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface UserRepo extends JpaRepository<User,Long> {
    @Query("SELECT s FROM User s WHERE s.userName=?1")
    Optional<User> findUserByUserName(String userName);
    @Query("SELECT s FROM User s WHERE s.email=?1")
    Optional<User> findUserByEmail(String email);

}
