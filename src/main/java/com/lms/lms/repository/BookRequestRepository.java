package com.lms.lms.repository;

import com.lms.lms.model.BaseUser;
import com.lms.lms.model.Book;
import com.lms.lms.model.BookRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRequestRepository extends JpaRepository<BookRequest,Long> {
    BookRequest findBookRequestByBaseUserAndBook(BaseUser baseUser, Book book);
}
