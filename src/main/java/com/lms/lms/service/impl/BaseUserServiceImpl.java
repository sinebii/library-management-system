package com.lms.lms.service.impl;


import com.lms.lms.exception.BookException;
import com.lms.lms.exception.UserException;
import com.lms.lms.model.BAuthor;
import com.lms.lms.model.BaseUser;
import com.lms.lms.model.Book;
import com.lms.lms.payload.request.CreateUserRequest;
import com.lms.lms.payload.request.UpdateUserRequest;
import com.lms.lms.payload.response.CreateUserResponse;
import com.lms.lms.repository.BookRepository;
import com.lms.lms.repository.UserRepository;
import com.lms.lms.service.BaseUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class BaseUserServiceImpl implements BaseUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
         if(userRepository.findAllByEmail(createUserRequest.getEmail())!=null) throw new UserException("User already exist");
         BaseUser baseUser = BaseUser.builder()
                 .firstName(createUserRequest.getFirstName())
                 .lastName(createUserRequest.getLastName())
                 .email(createUserRequest.getEmail())
                 .password(createUserRequest.getPassword())
                 .createdDate(Instant.now())
                 .lastModifiedDate(Instant.now())
                 .build();
         BaseUser savedUser = userRepository.save(baseUser);

        return mapper.map(savedUser, CreateUserResponse.class);
    }

    @Override
    public List<Book> getBorrowedBooksByUser(Long userId) {
        BaseUser user = userRepository.findById(userId).orElseThrow(()-> new UserException("User not found"));
        return user.getBorrowedBooks();

    }

    @Override
    public String updateUser(Long userId, UpdateUserRequest updateUserRequest) {
        String message = "";
        BaseUser user = userRepository.findById(userId).orElseThrow(()->new UserException("User not found"));

       user.setFirstName(updateUserRequest.getFirstName());
       user.setLastName(updateUserRequest.getLastName());
       user.setEmail(updateUserRequest.getEmail());
        userRepository.save(user);
        message = "Account successfully updated ";
        return message;
    }

}
