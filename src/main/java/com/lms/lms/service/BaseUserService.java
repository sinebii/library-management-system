package com.lms.lms.service;

import com.lms.lms.model.BaseUser;
import com.lms.lms.payload.request.CreateUserRequest;

public interface BaseUserService {
    BaseUser createUser(CreateUserRequest createUserRequest);
}
