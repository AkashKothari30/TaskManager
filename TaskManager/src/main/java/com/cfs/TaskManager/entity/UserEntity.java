package com.cfs.TaskManager.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 30)
    private String name;
    @Column(nullable = false,length = 50)
    private String email;

    @Column(nullable = false)
    private String password;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt =LocalDateTime.now();
    }
}
