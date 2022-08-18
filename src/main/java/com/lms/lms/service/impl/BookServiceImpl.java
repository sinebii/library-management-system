package com.lms.lms.service.impl;

import com.lms.lms.exception.BookException;
import com.lms.lms.exception.PublisherException;
import com.lms.lms.exception.UserException;
import com.lms.lms.model.BAuthor;
import com.lms.lms.model.BaseUser;
import com.lms.lms.model.Book;
import com.lms.lms.model.Publisher;
import com.lms.lms.payload.request.CreateBookRequest;
import com.lms.lms.payload.request.UpdateBookQtyRequest;
import com.lms.lms.payload.response.CreateBookResponse;
import com.lms.lms.payload.response.UpdateBookResponse;
import com.lms.lms.repository.AuthorRepository;
import com.lms.lms.repository.BookRepository;
import com.lms.lms.repository.PublisherRepository;
import com.lms.lms.repository.UserRepository;
import com.lms.lms.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public CreateBookResponse createNewBook(CreateBookRequest createBookRequest, Long authorId, Long publisherId) {
        if(bookRepository.findByIsbn(createBookRequest.getIsbn())!=null) throw new BookException("This book already exist");
        BAuthor bookAuthor = authorRepository.findById(authorId).orElseThrow(()->new BookException("Author does not exist"));
        Publisher publisher = publisherRepository.findById(publisherId).orElseThrow(()->new PublisherException("Publisher not found "+publisherId));
        Book book = Book.builder()
                .bookName(createBookRequest.getBookName())
                .bookYear(createBookRequest.getBookYear())
                .isbn(createBookRequest.getIsbn())
                .availableQuantity(0L)
                .bAuthor(bookAuthor)
                .publisher(publisher)
                .build();
        Book savedBook = bookRepository.save(book);
        System.out.println(book);
        return  mapper.map(savedBook,CreateBookResponse.class);
    }

    @Override
    public String borrowBook(Long bookId, Long userId) {
        List<Book> listOfBorrowedBooks;
        String message = "";
        BaseUser user = userRepository.findById(userId).orElseThrow(()->new UserException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(()->new BookException("Book not found"));
        if(book.getAvailableQuantity() <=0) throw new BookException("This book is currently not available");


        listOfBorrowedBooks = user.getBorrowedBooks();
        if(listOfBorrowedBooks.contains(book)){
            throw new BookException("You already borrowed this book");
        }else{
            listOfBorrowedBooks.add(book);
            user.setBorrowedBooks(listOfBorrowedBooks);
            book.setAvailableQuantity(book.getAvailableQuantity()-1);
            userRepository.save(user);
            bookRepository.save(book);
            message = "Book was successfully borrowed by "+user.getFirstName();
        }


        return message;
    }

    @Override
    public UpdateBookResponse updateBookQty(Long bookId, UpdateBookQtyRequest bookQty) {
        Book bookToUpdate = bookRepository.findById(bookId).orElseThrow(()->new BookException("Book not found"));
        if(bookQty.getAvailableQuantity() <= 0) throw new BookException("Invalid format, Please enter a value above 0");
        bookToUpdate.setAvailableQuantity(bookToUpdate.getAvailableQuantity()+bookQty.getAvailableQuantity());
        bookRepository.save(bookToUpdate);
        return mapper.map(bookToUpdate,UpdateBookResponse.class);
    }

}
