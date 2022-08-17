package com.lms.lms.repository;

import com.lms.lms.model.BaseUser;
import com.lms.lms.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    Book findByIsbn(Long isbn);

//    @Query(value = "SELECT bk FROM Book bk WHERE bk. = ?1 and bk.user= ?2")
//    Book findBorrowedBook(Book book, BaseUser baseUser);
}
