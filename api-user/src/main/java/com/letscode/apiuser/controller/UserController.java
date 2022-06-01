package com.letscode.apiuser.controller;


import com.letscode.apiuser.domain.UserEntity;
import com.letscode.apiuser.domain.UserRequest;
import com.letscode.apiuser.domain.UserResponse;
import com.letscode.apiuser.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        UserEntity entity = userService.toEntity(request);
        userService.saveEntity(entity);
        return new ResponseEntity<>(new UserResponse(entity), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer userId) {
        UserEntity entity = userService.getById(userId);
        return new ResponseEntity<>(new UserResponse(entity),HttpStatus.ACCEPTED);
    }

}
