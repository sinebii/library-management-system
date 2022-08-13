package com.lms.lms.controller;

import com.lms.lms.payload.request.CreateBookRequest;
import com.lms.lms.payload.response.CreateBookResponse;
import com.lms.lms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<CreateBookResponse> createBook(@RequestBody CreateBookRequest createBookRequest){
        return new ResponseEntity<>(bookService.createNewBook(createBookRequest), HttpStatus.CREATED);
    }
}
