package com.lms.lms.repository;

import com.lms.lms.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Publisher findByEmail(String email);
    Publisher findByPublisherId(Long publisherId);
}
