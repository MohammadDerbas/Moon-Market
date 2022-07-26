package com.example.demo.repo;

import com.example.demo.entity.ImgProfilePic;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@NoRepositoryBean
@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    @Query("SELECT s FROM User s WHERE s.email=?1")
    Optional<User> findUserByEmail(String email);
    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);
    @Transactional
    @Modifying
    @Query("update User u set u.imgProfilePic=?1")
    void updateUserImage(ImgProfilePic imageProfilePic);
    @Transactional
    @Modifying
    @Query("update User u set u.password=?1 where u=?2")
    void updatePassword(String password,User user);
    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = False WHERE a.email = ?1")
    int disableAppUser(String email);
    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.locked = true WHERE a.email = ?1")
    int lockAppUser(String email);
}
