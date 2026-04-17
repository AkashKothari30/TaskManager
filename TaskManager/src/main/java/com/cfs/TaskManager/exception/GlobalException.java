package com.cfs.TaskManager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,Object>>  handleRuntimeException(RuntimeException exception){
        Map<String,Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("massage",exception.getMessage());
        error.put("status", HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.badRequest().body(error);
    }

    public ResponseEntity<Map<String,Object>> handleException(Exception ex){
        Map<String,Object> error = new HashMap<>();
        error.put("timestamp",LocalDateTime.now());
        error.put("massage" ,"something went wrong" +  ex.getMessage());
        error.put("status",HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.badRequest().body(error);
    }
}
