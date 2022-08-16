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

    @JsonIgnore
    @ManyToMany(mappedBy = "borrowedBooks")
    private List<BaseUser> user;

    @ManyToOne
    private BAuthor bAuthor;

    @ManyToOne
    private Publisher publisher;


}
