package com.cfs.TaskManager.repository;

import com.cfs.TaskManager.entity.TaskEntity;
import com.cfs.TaskManager.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskEntity,Long> {

    Optional<TaskEntity> findByTitle(String title);
    List<TaskEntity> findByStatus(TaskStatus status);
    List<TaskEntity> findByTitleContaining(String keyword);
}
