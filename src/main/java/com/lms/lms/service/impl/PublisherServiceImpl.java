package com.lms.lms.service.impl;

import com.lms.lms.exception.UserException;
import com.lms.lms.model.Publisher;
import com.lms.lms.payload.request.CreatePublisherRequest;
import com.lms.lms.payload.response.CreatePublisherResponse;
import com.lms.lms.repository.PublisherRepository;
import com.lms.lms.service.PublisherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
@Service
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public CreatePublisherResponse createPublisher(CreatePublisherRequest createPublisherRequest) {
        if(publisherRepository.findById(createPublisherRequest.getPublisherId()).isPresent()) throw new UserException("Publisher exist");
        Publisher publisher = Publisher.builder()
                .firstName(createPublisherRequest.getFirstName())
                .lastName(createPublisherRequest.getLastName())
                .email(createPublisherRequest.getEmail())
                .password(createPublisherRequest.getPassword())
                .publisherId(createPublisherRequest.getPublisherId())
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .build();
        Publisher savedPublisher = publisherRepository.save(publisher);
        return mapper.map(savedPublisher,CreatePublisherResponse.class);
    }
}
