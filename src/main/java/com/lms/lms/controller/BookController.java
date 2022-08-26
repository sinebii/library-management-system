package com.lms.lms.controller;

import com.lms.lms.payload.request.CreateBookRequest;
import com.lms.lms.payload.request.RequestBookReturnBookRequest;
import com.lms.lms.payload.request.ActOnBookRequest;
import com.lms.lms.payload.request.UpdateBookQtyRequest;
import com.lms.lms.payload.response.CreateBookResponse;
import com.lms.lms.payload.response.UpdateBookResponse;
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
    public ResponseEntity<String> requestBook(@PathVariable(name = "bookId") Long bookId, @PathVariable(name = "userId") Long userId, @RequestBody RequestBookReturnBookRequest requestBookReturnBookRequest){
        return  new ResponseEntity<>(bookService.requestBookReturnBook(bookId,userId,requestBookReturnBookRequest),HttpStatus.OK);
    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<UpdateBookResponse> updateBook(@RequestBody UpdateBookQtyRequest updateBookQtyRequest, @PathVariable(name = "bookId")Long bookId){
        return new ResponseEntity<>(bookService.updateBookQty(bookId, updateBookQtyRequest), HttpStatus.OK);
    }
    @PutMapping("/return-book/{bookId}/{userId}")
    public ResponseEntity<String> returnBook(@PathVariable(name = "bookId") Long bookId, @PathVariable(name = "userId") Long userId, @RequestBody RequestBookReturnBookRequest requestBookReturnBookRequest){
        return new ResponseEntity<>(bookService.requestBookReturnBook(bookId,userId,requestBookReturnBookRequest), HttpStatus.OK);
    }

    @PutMapping("/act-on-request/{requestId}")
    public ResponseEntity<String> actOnBookRequest(@RequestBody ActOnBookRequest actOnBookRequest, @PathVariable(name = "requestId") Long requestId){
        return new ResponseEntity<>(bookService.actOnBookRequest(requestId, actOnBookRequest), HttpStatus.OK);
    }

}
