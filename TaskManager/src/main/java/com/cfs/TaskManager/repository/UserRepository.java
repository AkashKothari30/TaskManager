package com.cfs.TaskManager.repository;

import com.cfs.TaskManager.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
}
