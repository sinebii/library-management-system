package com.lms.lms.service.impl;


import com.lms.lms.exception.UserException;
import com.lms.lms.model.BaseUser;
import com.lms.lms.model.Book;
import com.lms.lms.payload.request.CreateUserRequest;
import com.lms.lms.payload.request.UpdatePassword;
import com.lms.lms.payload.request.UpdateUserRequest;
import com.lms.lms.payload.response.CreateUserResponse;
import com.lms.lms.repository.UserRepository;
import com.lms.lms.service.BaseUserService;
import com.lms.lms.utils.UtilsHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class BaseUserServiceImpl implements BaseUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UtilsHelper utilsHelper;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
         if(userRepository.findAllByEmail(createUserRequest.getEmail())!=null) throw new UserException("User already exist");
         BaseUser baseUser = BaseUser.builder()
                 .uId(utilsHelper.generateRandom(30))
                 .firstName(createUserRequest.getFirstName())
                 .lastName(createUserRequest.getLastName())
                 .email(createUserRequest.getEmail())
                 .emailVerificationStatus(false)
                 .emailVerificationToke(utilsHelper.generateRandom(20))
                 .password(bCryptPasswordEncoder.encode(createUserRequest.getPassword()))
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
        String message;
        BaseUser user = userRepository.findById(userId).orElseThrow(()->new UserException("User not found"));

       user.setFirstName(updateUserRequest.getFirstName());
       user.setLastName(updateUserRequest.getLastName());
       user.setEmail(updateUserRequest.getEmail());
        userRepository.save(user);
        message = "Account successfully updated ";
        return message;
    }

    @Override
    public String updatePassword(Long userId, UpdatePassword updatePassword) {
        String message;
        BaseUser user = userRepository.findById(userId).orElseThrow(()->new UserException("User not found"));
        user.setPassword(updatePassword.getPassword());
        userRepository.save(user);
        message = "Password was successfully saved";
        return message;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        BaseUser baseUser = userRepository.findAllByEmail(email);
        if(baseUser ==null) throw new UsernameNotFoundException(email);
        return new User(baseUser.getEmail(), baseUser.getPassword(), new ArrayList<>());
    }
}
