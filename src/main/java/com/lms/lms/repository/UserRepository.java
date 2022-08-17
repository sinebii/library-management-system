package com.lms.lms.repository;

import com.lms.lms.model.BaseUser;
import com.lms.lms.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<BaseUser, Long> {

    BaseUser findAllByEmail(String email);

}
