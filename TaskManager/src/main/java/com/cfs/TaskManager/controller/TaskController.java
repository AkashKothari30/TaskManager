package com.cfs.TaskManager.controller;

import com.cfs.TaskManager.dto.TaskRequest;
import com.cfs.TaskManager.entity.TaskEntity;
import com.cfs.TaskManager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    private ResponseEntity<TaskEntity> createTask(@RequestBody TaskRequest request){
        return ResponseEntity.ok(taskService.createTask(request));
    }

    @GetMapping
    private ResponseEntity<List<TaskEntity>> getAllTask(){
        return ResponseEntity.ok(taskService.getAllTask());
    }
    @GetMapping("/{id}")
    private ResponseEntity<TaskEntity> getTaskById(@PathVariable Long id){
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PutMapping("/id{task}")
    private ResponseEntity<TaskEntity> updateTask(@PathVariable Long id,@RequestBody TaskEntity task){
        return ResponseEntity.ok(taskService.updateTask(id,task));
    }
    @DeleteMapping("/id")
    private ResponseEntity<String> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task delete successfully..");
    }
}
