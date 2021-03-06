package com.api.pipeline.erp.repository;

import com.api.pipeline.erp.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findAllByUserId(String userId);
    Optional<User> findUserByUserId(String userId);
}
