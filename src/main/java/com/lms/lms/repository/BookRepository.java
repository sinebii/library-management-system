package com.lms.lms.repository;

import com.lms.lms.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
    Book findByIsbn(Long isbn);
}
