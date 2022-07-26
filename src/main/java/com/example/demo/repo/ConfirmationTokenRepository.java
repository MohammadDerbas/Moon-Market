package com.example.demo.repo;

import com.example.demo.entity.ConfirmationToken;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {

    @Query("select c from ConfirmationToken c where c.token = ?1")
    Optional<ConfirmationToken> findByToken(String token);
    @Transactional
    @Modifying
    @Query(
            "update ConfirmationToken c set c.confirmedAt=?2 where c.token=?1"
    )
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);

    @Query("select c.user from  ConfirmationToken  c where c.token=?1")
    User getUserByToken(String token);
    @Query("select (count(c) > 0) from ConfirmationToken c where c.token = ?1")
    boolean existsByToken(String token);
}
