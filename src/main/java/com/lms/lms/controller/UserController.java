package com.lms.lms.controller;

import com.lms.lms.model.Book;
import com.lms.lms.payload.request.CreateUserRequest;
import com.lms.lms.payload.response.CreateUserResponse;
import com.lms.lms.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private BaseUserService baseUserService;

    @PostMapping
    public ResponseEntity<CreateUserResponse> createNewUser(@RequestBody CreateUserRequest createUserRequest){
        return new ResponseEntity<>(baseUserService.createUser(createUserRequest), HttpStatus.OK);
    }

    @GetMapping("/get-books/{userId}")
    public ResponseEntity<List<Book>> getListOfBorrowedBooks(@PathVariable (name = "userId")Long userId){
        return new ResponseEntity<>(baseUserService.getBorrowedBooksByUser(userId),HttpStatus.OK);
    }
}
