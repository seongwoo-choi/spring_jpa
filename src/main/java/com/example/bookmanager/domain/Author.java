package com.example.bookmanager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Author extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;

    @ManyToMany
    @ToString.Exclude
    private List<Book> books = new ArrayList<>();

    // Book... => 값을 배열 형태로 받는다.
    // Collections 의 addAll 을 사용해서 배열에 입력받은 값을 추가한다.
    public void addBook(Book... book) {
        Collections.addAll(this.books, book);
    }
}
