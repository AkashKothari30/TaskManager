package com.cfs.TaskManager.entity;

import com.cfs.TaskManager.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tasks")
@Getter  @Setter
@NoArgsConstructor  @AllArgsConstructor
@Builder
public class TaskEntity {

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private UserEntity user;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;




    @Enumerated(EnumType.STRING)
    private TaskStatus status;
}
