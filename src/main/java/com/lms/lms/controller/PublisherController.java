package com.lms.lms.controller;

import com.lms.lms.payload.request.CreatePublisherRequest;
import com.lms.lms.payload.response.CreatePublisherResponse;
import com.lms.lms.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/publisher")
public class PublisherController {

        @Autowired
        private PublisherService publisherService;

        @PostMapping
        public ResponseEntity<CreatePublisherResponse> createAuthor(@RequestBody CreatePublisherRequest createPublisherRequest){
            return new ResponseEntity<>(publisherService.createPublisher(createPublisherRequest), HttpStatus.CREATED);
        }
}
