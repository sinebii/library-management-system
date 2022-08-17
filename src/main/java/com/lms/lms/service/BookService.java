package com.lms.lms.service;

import com.lms.lms.model.BaseUser;
import com.lms.lms.model.Book;
import com.lms.lms.payload.request.CreateBookRequest;
import com.lms.lms.payload.response.CreateBookResponse;

public interface BookService {
    CreateBookResponse createNewBook(CreateBookRequest createBookRequest, Long authorId, Long publisherId);
    String borrowBook(Long bookId, Long userId);
}
