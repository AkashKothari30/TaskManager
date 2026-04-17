package com.cfs.TaskManager.service;

import com.cfs.TaskManager.dto.UserRequest;
import com.cfs.TaskManager.entity.TaskEntity;
import com.cfs.TaskManager.entity.UserEntity;
import com.cfs.TaskManager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

  public UserEntity register(UserRequest  request){
    if(userRepository.existsByEmail(request.getEmail())){
        throw new RuntimeException("Email already exist" + request.getEmail());
    }

    UserEntity user = UserEntity.builder()
            .name(request.getName())
            .email(request.getEmail())
            .password(request.getPassword())
            .build();

    return userRepository.save(user);
  }

  public UserEntity getUserById(Long id){
      return userRepository.findById(id).orElseThrow(()->new RuntimeException("user not found by id" + id));
  }

  public List<UserEntity> getAllUser(){
      return userRepository.findAll();
  }

  public UserEntity updateUser(Long id,UserEntity user){

      UserEntity users = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found" + id));
      users.setName(user.getName());
      return userRepository.save(users);
  }

  public void deleteUser(Long id){
      userRepository.deleteById(id);
  }
}
