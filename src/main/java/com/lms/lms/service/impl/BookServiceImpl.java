package com.lms.lms.service.impl;

import com.lms.lms.exception.BookException;
import com.lms.lms.model.Book;
import com.lms.lms.payload.request.CreateBookRequest;
import com.lms.lms.payload.response.CreateBookResponse;
import com.lms.lms.repository.BookRepository;
import com.lms.lms.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public CreateBookResponse createNewBook(CreateBookRequest createBookRequest) {
        if(bookRepository.findByIsbn(createBookRequest.getIsbn())!=null) throw new BookException("This book already exist");
        Book book = Book.builder()
                .bookName(createBookRequest.getBookName())
                .isbn(createBookRequest.getIsbn())
                .bAuthor(createBookRequest.getBAuthor())
                .publisher(createBookRequest.getPublisher())
                .bookYear(createBookRequest.getBookYear())
                .build();
        Book savedBook = bookRepository.save(book);
        return  mapper.map(savedBook,CreateBookResponse.class);
    }
}
