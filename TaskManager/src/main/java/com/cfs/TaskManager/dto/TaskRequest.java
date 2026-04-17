package com.cfs.TaskManager.dto;


import com.cfs.TaskManager.entity.UserEntity;
import com.cfs.TaskManager.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter  @Setter
@NoArgsConstructor   @AllArgsConstructor
public class TaskRequest {

    private String title;
    private String description;
    private TaskStatus status;
    private UserEntity userid;
}
