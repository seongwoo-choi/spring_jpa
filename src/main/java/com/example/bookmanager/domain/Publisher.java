package com.example.bookmanager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Publisher extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // CASCADE 는 말그대로 상위 객체가 remove 액션을 하면 하위 엔티티 까지 remove 시키는 액션
    // 하지만 연관관계가 끊어질 때(setter 로 null 을 만드는 경우)는 remove 액션이 발생하지 않는다.
    // 연관관계가 전혀 없는 엔티티 데이터(고아데이터)를 없애기 위해서는 orphanRemoval = true 로 설정하면 된다.
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "publisher_id")
    @ToString.Exclude
    private List<Book> books = new ArrayList<>();

    // 본인의 books 에 book 을 추가해준다.
    public void addBook(Book book) {
        this.books.add(book);
    }
}
