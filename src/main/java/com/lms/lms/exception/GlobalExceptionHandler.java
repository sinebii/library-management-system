package com.lms.lms.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BookException.class)
    public ResponseEntity<?> handleBookErrorEx(BookException ex, WebRequest request){
        ApiErrorDetail apiErrorDetail = new ApiErrorDetail(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiErrorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> handleUserErrorEx(UserException ex, WebRequest request){
        ApiErrorDetail apiErrorDetail = new ApiErrorDetail(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiErrorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PublisherException.class)
    public ResponseEntity<?> handlePublisherErrorEx(PublisherException ex, WebRequest request){
        ApiErrorDetail apiErrorDetail = new ApiErrorDetail(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiErrorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookRequestException.class)
    public ResponseEntity<?> handlePublisherErrorEx(BookRequestException ex, WebRequest request){
        ApiErrorDetail apiErrorDetail = new ApiErrorDetail(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiErrorDetail, HttpStatus.NOT_FOUND);
    }


}
