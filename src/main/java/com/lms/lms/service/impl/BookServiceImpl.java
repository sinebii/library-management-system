package com.lms.lms.service.impl;

import com.lms.lms.exception.BookException;
import com.lms.lms.exception.PublisherException;
import com.lms.lms.exception.UserException;
import com.lms.lms.model.BAuthor;
import com.lms.lms.model.BaseUser;
import com.lms.lms.model.Book;
import com.lms.lms.model.Publisher;
import com.lms.lms.payload.request.CreateBookRequest;
import com.lms.lms.payload.response.CreateBookResponse;
import com.lms.lms.repository.AuthorRepository;
import com.lms.lms.repository.BookRepository;
import com.lms.lms.repository.PublisherRepository;
import com.lms.lms.repository.UserRepository;
import com.lms.lms.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Book borrowBook(Long bookId, Long userId) {

        List<Book> listOfBorrowedBooks = null;
        BaseUser user = userRepository.findById(userId).orElseThrow(()->new UserException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(()->new BookException("Book not found"));
        if(book.getAvailableQuantity() <=0) throw new BookException("This book is currently not available");
        System.out.println("USER FIRST NAME:"+user.getFirstName());
        if(checkIfUserCanBorrowBook(user, bookId)) throw new BookException("Already Borrowed Book");
        listOfBorrowedBooks = user.getBorrowedBooks();
        for(int i=0; i<user.getBorrowedBooks().size();i++){
            if(!user.getBorrowedBooks().get(i).getBookId().equals(bookId)){
                listOfBorrowedBooks.add(book);
                user.setBorrowedBooks(listOfBorrowedBooks);
                book.setAvailableQuantity(book.getAvailableQuantity()-1);

            }
        }



        bookRepository.save(book);
        userRepository.save(user);
        return book;


    }
    public boolean checkIfUserCanBorrowBook(BaseUser user, Long bookId){
        boolean status = false;
        int bookSize = user.getBorrowedBooks().size();
        for(int i=0; i< bookSize;i++){
            status = !user.getBorrowedBooks().get(i).getBookId().equals(bookId);
        }
        return status;
    }
}
