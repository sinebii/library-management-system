package com.lms.lms.service;

import com.lms.lms.model.BaseUser;
import com.lms.lms.model.Book;
import com.lms.lms.payload.request.CreateUserRequest;
import com.lms.lms.payload.request.UpdatePassword;
import com.lms.lms.payload.request.UpdateUserRequest;
import com.lms.lms.payload.response.CreateUserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface BaseUserService  extends UserDetailsService {
    CreateUserResponse createUser(CreateUserRequest createUserRequest);
    List<Book> getBorrowedBooksByUser(Long userId);
    String updateUser(Long userId, UpdateUserRequest updateUserRequest);
    String updatePassword(Long userId, UpdatePassword updatePassword);
    CreateUserResponse getUser(String email);

}
