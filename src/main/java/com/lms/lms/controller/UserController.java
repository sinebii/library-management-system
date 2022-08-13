package com.lms.lms.controller;

import com.lms.lms.payload.request.CreateUserRequest;
import com.lms.lms.payload.response.CreateUserResponse;
import com.lms.lms.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private BaseUserService baseUserService;

    @PostMapping
    public ResponseEntity<CreateUserResponse> createNewUser(@RequestBody CreateUserRequest createUserRequest){
        return new ResponseEntity<>(baseUserService.createUser(createUserRequest), HttpStatus.OK);
    }
}
