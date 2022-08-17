package com.lms.lms.service;

import com.lms.lms.model.BaseUser;
import com.lms.lms.model.Book;
import com.lms.lms.payload.request.CreateBookRequest;
import com.lms.lms.payload.request.UpdateBookQtyRequest;
import com.lms.lms.payload.response.CreateBookResponse;
import com.lms.lms.payload.response.UpdateBookResponse;

public interface BookService {
    CreateBookResponse createNewBook(CreateBookRequest createBookRequest, Long authorId, Long publisherId);
    String borrowBook(Long bookId, Long userId);
    UpdateBookResponse updateBookQty(Long bookId, UpdateBookQtyRequest bookQty);
}
