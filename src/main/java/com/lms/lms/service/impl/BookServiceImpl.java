package com.lms.lms.service.impl;

import com.lms.lms.exception.BookException;
import com.lms.lms.exception.PublisherException;
import com.lms.lms.model.BAuthor;
import com.lms.lms.model.Book;
import com.lms.lms.model.Publisher;
import com.lms.lms.payload.request.CreateBookRequest;
import com.lms.lms.payload.response.CreateBookResponse;
import com.lms.lms.repository.AuthorRepository;
import com.lms.lms.repository.BookRepository;
import com.lms.lms.repository.PublisherRepository;
import com.lms.lms.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public CreateBookResponse createNewBook(CreateBookRequest createBookRequest, Long authorId, Long publisherId) {
        if(bookRepository.findByIsbn(createBookRequest.getIsbn())!=null) throw new BookException("This book already exist");
        BAuthor bookAuthor = authorRepository.findById(authorId).orElseThrow(()->new BookException(" Book not found"));
        Publisher publisher = publisherRepository.findById(publisherId).orElseThrow(()->new PublisherException(" Publisher not found"));
        Book book = Book.builder()
                .bookName(createBookRequest.getBookName())
                .bookYear(createBookRequest.getBookYear())
                .isbn(createBookRequest.getIsbn())
                .bAuthor(bookAuthor)
                .publisher(publisher)
                .build();
        Book savedBook = bookRepository.save(book);
        System.out.println(book);
        return  mapper.map(savedBook,CreateBookResponse.class);
    }
}
