package com.lms.lms.service;

import com.lms.lms.payload.request.CreateBookRequest;
import com.lms.lms.payload.response.CreateBookResponse;

public interface BookService {
    CreateBookResponse createNewBook(CreateBookRequest createBookRequest);
}
