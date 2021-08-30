package com.api.pipeline.erp.repository;

import com.api.pipeline.erp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
