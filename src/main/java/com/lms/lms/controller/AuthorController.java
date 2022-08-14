package com.lms.lms.controller;

import com.lms.lms.model.BAuthor;
import com.lms.lms.payload.request.CreateAuthorRequest;
import com.lms.lms.payload.response.CreateAuthorResponse;
import com.lms.lms.payload.response.CreatePublisherResponse;
import com.lms.lms.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/create")
    public ResponseEntity<CreateAuthorResponse> createAuthor(@RequestBody CreateAuthorRequest createAuthorRequest){
        return new ResponseEntity<>(authorService.createAuthor(createAuthorRequest), HttpStatus.OK);
    }
}
