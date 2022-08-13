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
public class BAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bAuthorId;
    private String authorName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Book> book;


}
