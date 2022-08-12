package com.lms.lms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Author extends BaseUser{
    private Long authorId;
    @ManyToMany
    private List<Publisher> publisher;

    @ManyToMany
    private List<Book> book = new ArrayList<>();
}
