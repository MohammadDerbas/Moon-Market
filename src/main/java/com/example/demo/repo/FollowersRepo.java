package com.example.demo.repo;

import com.example.demo.entity.Followers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowersRepo extends JpaRepository<Followers,Long> {
}
