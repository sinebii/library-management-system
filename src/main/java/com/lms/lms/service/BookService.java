package com.lms.lms.service;

import com.lms.lms.payload.request.CreateBookRequest;
import com.lms.lms.payload.request.RequestBookReturnBookRequest;
import com.lms.lms.payload.request.ActOnBookRequest;
import com.lms.lms.payload.request.UpdateBookQtyRequest;
import com.lms.lms.payload.response.CreateBookResponse;
import com.lms.lms.payload.response.UpdateBookResponse;

public interface BookService {
    CreateBookResponse createNewBook(CreateBookRequest createBookRequest, Long authorId, Long publisherId);
    String requestBookReturnBook(Long bookId, Long userId, RequestBookReturnBookRequest requestBookReturnBookRequest);
    String actOnBookRequest(Long requestId, ActOnBookRequest actOnBookRequest);
    UpdateBookResponse updateBookQty(Long bookId, UpdateBookQtyRequest bookQty);
//    String returnBorrowedBook(Long bookId, Long userId);
}
