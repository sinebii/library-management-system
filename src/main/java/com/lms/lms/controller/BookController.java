package com.lms.lms.controller;

import com.lms.lms.model.Book;
import com.lms.lms.payload.request.CreateBookRequest;
import com.lms.lms.payload.response.CreateBookResponse;
import com.lms.lms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/create/{authorId}/{publisherId}")
    public ResponseEntity<CreateBookResponse> createBook(@RequestBody CreateBookRequest createBookRequest,@PathVariable(name = "authorId") Long authorId, @PathVariable(name = "publisherId")Long publisherId){
        return new ResponseEntity<>(bookService.createNewBook(createBookRequest, authorId,publisherId), HttpStatus.CREATED);
    }
    @PostMapping("/borrow/{bookId}/{userId}")
    public ResponseEntity<String> borrowBook(@PathVariable(name = "bookId") Long bookId, @PathVariable(name = "userId") Long userId){
        return  new ResponseEntity<>(bookService.borrowBook(bookId,userId),HttpStatus.OK);
    }

}
