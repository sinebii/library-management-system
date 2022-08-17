package com.lms.lms.service;

import com.lms.lms.model.BaseUser;
import com.lms.lms.model.Book;
import com.lms.lms.payload.request.CreateUserRequest;
import com.lms.lms.payload.response.CreateUserResponse;

import java.util.List;

public interface BaseUserService {
    CreateUserResponse createUser(CreateUserRequest createUserRequest);
    List<Book> getBorrowedBooksByUser(Long userId);

}
