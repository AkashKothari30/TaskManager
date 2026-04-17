package com.cfs.TaskManager.controller;

import com.cfs.TaskManager.dto.UserRequest;
import com.cfs.TaskManager.entity.UserEntity;
import com.cfs.TaskManager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    private ResponseEntity<UserEntity> registetr (@RequestBody UserRequest request){
        return ResponseEntity.ok(userService.register(request));
    }

    @GetMapping("/{id}")
    private ResponseEntity<UserEntity> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    private ResponseEntity<List<UserEntity>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }
    @PutMapping("/{id}")
    private ResponseEntity<UserEntity> updateUser(@PathVariable Long id,@RequestBody UserEntity user){
        return ResponseEntity.ok(userService.updateUser(id,user));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully...");
    }
}
