package com.lms.lms.repository;

import com.lms.lms.model.BaseUser;
import com.lms.lms.model.Book;
import com.lms.lms.model.BookRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRequestRepository extends JpaRepository<BookRequest,Long> {
    List<BookRequest> findBookRequestByBaseUserAndBook(BaseUser baseUser, Book book);
}
