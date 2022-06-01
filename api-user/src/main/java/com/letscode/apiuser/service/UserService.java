package com.letscode.apiuser.service;


import com.letscode.apiuser.domain.UserEntity;
import com.letscode.apiuser.domain.UserRequest;
import com.letscode.apiuser.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserEntity saveEntity(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity getById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserEntity toEntity(UserRequest request) {
        return new UserEntity(
                request.getName(),
                request.getUserName(),
                request.getEmail(),
                ZonedDateTime.now(),
                ZonedDateTime.now()
        );
    }

}
