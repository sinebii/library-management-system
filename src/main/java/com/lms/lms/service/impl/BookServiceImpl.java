package com.lms.lms.service.impl;

import com.lms.lms.enums.BookRequestStatus;
import com.lms.lms.exception.BookException;
import com.lms.lms.exception.BookRequestException;
import com.lms.lms.exception.PublisherException;
import com.lms.lms.exception.UserException;
import com.lms.lms.model.*;
import com.lms.lms.payload.request.ActOnBookRequest;
import com.lms.lms.payload.request.CreateBookRequest;
import com.lms.lms.payload.request.RequestBookReturnBookRequest;
import com.lms.lms.payload.request.UpdateBookQtyRequest;
import com.lms.lms.payload.response.CreateBookResponse;
import com.lms.lms.payload.response.UpdateBookResponse;
import com.lms.lms.repository.*;
import com.lms.lms.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.List;

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
    @Autowired
    private BookRequestRepository bookRequestRepository;

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
    public String requestBookReturnBook(Long bookId, Long userId, RequestBookReturnBookRequest requestBookReturnBookRequest) {

        String message = "";
        BaseUser user = userRepository.findById(userId).orElseThrow(()->new UserException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(()->new BookException("Book not found"));
        if(book.getAvailableQuantity() <=0) throw new BookException("This book is currently not available");
        if(bookRequestRepository.findBookRequestByBaseUserAndBook(user,book)!=null && bookRequestRepository.findBookRequestByBaseUserAndBook(user,book).getStatus()==BookRequestStatus.PENDING_APPROVE ) throw new BookRequestException("You have an existing request for this book");
        if(bookRequestRepository.findBookRequestByBaseUserAndBook(user,book).getStatus()==BookRequestStatus.PENDING_RETURN ) throw new BookRequestException("You have an existing RETURN request for this book");
        if(requestBookReturnBookRequest.getBookRequestStatus().equals(BookRequestStatus.PENDING_APPROVE)){
            if(user.getBorrowedBooks().contains(book))throw new BookException("You already have this book on your list");
            BookRequest bookRequest = BookRequest.builder()
                    .baseUser(user)
                    .status(BookRequestStatus.PENDING_APPROVE)
                    .book(book)
                    .createdDate(Instant.now())
                    .lastModifiedDate(Instant.now())
                    .build();
            bookRequestRepository.save(bookRequest);
            message = "Your book request was sent successfully ";
        }else if(requestBookReturnBookRequest.getBookRequestStatus().equals(BookRequestStatus.PENDING_RETURN)){
            BookRequest bookRequest = BookRequest.builder()
                    .status(BookRequestStatus.PENDING_RETURN)
                    .baseUser(user)
                    .book(book)
                    .createdDate(Instant.now())
                    .lastModifiedDate(Instant.now())
                    .build();
            bookRequestRepository.save(bookRequest);
            message = "Your request to return book was received";
        }
        return message;
    }

    @Override
    public String actOnBookRequest(Long requestId, ActOnBookRequest actOnBookRequest) {
        String message = null;
        BookRequest bookRequest = bookRequestRepository.findById(requestId).orElseThrow(()->new BookRequestException("Book request not found"));
        BaseUser user = bookRequest.getBaseUser();
        Book book = bookRequest.getBook();
        List<Book> listOfBorrowedBooks;
        listOfBorrowedBooks = user.getBorrowedBooks();
            if(actOnBookRequest.getBookRequestStatus().equals(BookRequestStatus.APPROVE)){
                listOfBorrowedBooks.add(book);
                user.setBorrowedBooks(listOfBorrowedBooks);
                book.setAvailableQuantity(book.getAvailableQuantity()-1);
                bookRequest.setStatus(BookRequestStatus.APPROVE);
                userRepository.save(user);
                bookRepository.save(book);
                bookRequestRepository.save(bookRequest);
                message = "Book was successfully borrowed";

            }else if(actOnBookRequest.getBookRequestStatus().equals(BookRequestStatus.RETURN)){
                user.getBorrowedBooks().remove(book);
                user.setBorrowedBooks(user.getBorrowedBooks());
                book.setAvailableQuantity(book.getAvailableQuantity()+1);
                bookRequest.setStatus(BookRequestStatus.RETURN);
                userRepository.save(user);
                bookRepository.save(book);
                bookRequestRepository.save(bookRequest);
                message = "Book was successfully returned ";
            }else if(actOnBookRequest.getBookRequestStatus().equals(BookRequestStatus.DECLINE)){
                bookRequest.setStatus(BookRequestStatus.DECLINE);
                bookRequestRepository.save(bookRequest);
                message = "You declined the user request";
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

//    @Override
//    public String returnBorrowedBook(Long bookId, Long userId) {
//        String message = "";
//        Book bookToReturn = bookRepository.findById(bookId).orElseThrow(()->new BookException("Book not found"));
//        BaseUser user = userRepository.findById(userId).orElseThrow(()->new UserException("User not found"));
//
//        if(!user.getBorrowedBooks().contains(bookToReturn)) throw new BookException("Book  not on your list o borrowed");
//        user.getBorrowedBooks().remove(bookToReturn);
//        user.setBorrowedBooks(user.getBorrowedBooks());
//        bookToReturn.setAvailableQuantity(bookToReturn.getAvailableQuantity()+1);
//        userRepository.save(user);
//        bookRepository.save(bookToReturn);
//        message = "Book was successfully returned by "+user.getFirstName();
//        return message;
//
//    }

}
