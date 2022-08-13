package com.lms.lms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class BAuthor extends BaseUser {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bAuthorId;
    @OneToMany
    private List<Book> book;



}
