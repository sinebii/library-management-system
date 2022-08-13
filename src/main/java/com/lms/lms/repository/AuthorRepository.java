package com.lms.lms.repository;

import com.lms.lms.model.BAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<BAuthor, Long> {
}
