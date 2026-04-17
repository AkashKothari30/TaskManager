package com.cfs.TaskManager.service;

import com.cfs.TaskManager.dto.TaskRequest;
import com.cfs.TaskManager.entity.TaskEntity;
import com.cfs.TaskManager.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskEntity createTask(TaskRequest request){
        if(taskRepository.findByTitle(request.getTitle()).isPresent()){
            throw new RuntimeException("Task already exist" + request.getTitle());
        }
        TaskEntity task = TaskEntity.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .user(request.getUserid())
                .build();

        return taskRepository.save(task);
    }

    public List<TaskEntity> getAllTask(){
        return taskRepository.findAll();
    }

    public TaskEntity getTaskById(Long id){
        return taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Task not found" + id));
    }

    public TaskEntity updateTask(Long id,TaskEntity task){
         TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Task not found by id" + id));
         taskEntity.setTitle(task.getTitle());

         return taskRepository.save(taskEntity);
    }
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}
