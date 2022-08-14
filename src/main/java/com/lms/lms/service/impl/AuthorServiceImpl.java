package com.lms.lms.service.impl;

import com.lms.lms.exception.UserException;
import com.lms.lms.model.BAuthor;
import com.lms.lms.payload.request.CreateAuthorRequest;
import com.lms.lms.payload.response.CreateAuthorResponse;
import com.lms.lms.repository.AuthorRepository;
import com.lms.lms.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public CreateAuthorResponse createAuthor(CreateAuthorRequest createAuthorRequest) {
        if(authorRepository.findByEmail(createAuthorRequest.getEmail())) throw new UserException("User already exist");
        BAuthor author = BAuthor.builder()
                .firstName(createAuthorRequest.getFirstName())
                .lastName(createAuthorRequest.getLastName())
                .email(createAuthorRequest.getEmail())
                .password(createAuthorRequest.getPassword())
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .build();
        BAuthor savedAuthor = authorRepository.save(author);
        return mapper.map(savedAuthor, CreateAuthorResponse.class);
    }
}
