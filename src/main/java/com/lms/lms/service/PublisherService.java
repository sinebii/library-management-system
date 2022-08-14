package com.lms.lms.service;

import com.lms.lms.model.Publisher;
import com.lms.lms.payload.request.CreatePublisherRequest;
import com.lms.lms.payload.response.CreatePublisherResponse;

public interface PublisherService {
    CreatePublisherResponse createPublisher(CreatePublisherRequest createPublisherRequest);
}
