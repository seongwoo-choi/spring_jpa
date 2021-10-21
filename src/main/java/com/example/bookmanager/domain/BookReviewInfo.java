package com.example.bookmanager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class BookReviewInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private Long bookId;

    // 실제 객체를 참조
    // optional = false => null 을 허용하지 않겠다. 즉 book_id 는 반드시 존재한다.
    @OneToOne(optional = false)
    private Book book;

    // primitive 타입을 사용한 것(wrappered 타입(Float)과의 차이점) => null 체크를 하지 않으면 NullPointException 이 발생할 수 있다.
    private float averageReviewScore;

    // wrappered type(Integer) => null 체크를 하지 않으면 NullPointException 이 발생할 수 있다.
    private int reviewCount;
}
