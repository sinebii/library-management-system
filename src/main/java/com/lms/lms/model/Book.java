package com.lms.lms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String bookName;
    private Long isbn;
    private String bookYear;
    private Long availableQuantity;

    @ManyToOne
    private BAuthor bAuthor;

    @ManyToOne
    private Publisher publisher;

    @OneToMany
    private List<BookRequest> bookRequest;


}
