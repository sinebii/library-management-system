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
public class Publisher extends BaseUser{

    private Long publisherId;
    private String publisherCoyName;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Book> book;

}
