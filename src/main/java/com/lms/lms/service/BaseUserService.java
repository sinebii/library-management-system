package com.lms.lms.service;

import com.lms.lms.model.BaseUser;
import com.lms.lms.payload.request.CreateUserRequest;
import com.lms.lms.payload.response.CreateUserResponse;

public interface BaseUserService {
    CreateUserResponse createUser(CreateUserRequest createUserRequest);

    String borrowBook(Long userId, Long bookId);
}
