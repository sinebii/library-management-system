package com.lms.lms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String bookName;
    private Long isbn;
    private String bookYear;

    @ManyToOne
    private BAuthor bAuthors;

    @ManyToOne
    private Publisher publisher;


}
