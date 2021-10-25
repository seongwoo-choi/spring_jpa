package com.example.bookmanager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BookAndAuthor extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // 기존의 ManyToMany 관계의 테이블을 중간 테이블을 추가하여 각각 N:1, M:1 관계로 수정한다.
    @ManyToOne
    private Book book;

    @ManyToOne
    private Author author;

}
