package com.lms.lms.service;

import com.lms.lms.payload.request.CreateAuthorRequest;
import com.lms.lms.payload.response.CreateAuthorResponse;

public interface AuthorService {
    CreateAuthorResponse createAuthor(CreateAuthorRequest createAuthorRequest);
}
