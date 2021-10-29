package com.example.bookmanager.domain;

import com.example.bookmanager.domain.listener.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//@DynamicUpdate // => READ_UNCOMITTIED 에서 데이터가 업데이트 된 이후 롤백되는 케이스에서 데이터 정합성을 해친다. 그것을 막기 위해 사용
@Where(clause = "deleted = false") // where 절에 다음 조건들이 추가된다. (컬럼 중에서 deleted 의 값이 false 인 값)
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;


    private String category;


    private Long authorId;


//    private Long publisherId;


    @OneToOne(mappedBy = "book")
    @ToString.Exclude
    private BookReviewInfo bookReviewInfo;


    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();


    // book.setPublisher(publisher), bookRepository.save(book) 만 해주어도 자동으로 db 에 값이 저장된다.
    // 이게 CASCADE 영속성 전이
    @ManyToOne(cascade = {CascadeType.ALL})
    @ToString.Exclude
    private Publisher publisher;


    //    @ManyToMany
    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

    // 해당 속성이 true 이면 지워졌다고 처리
    // 이런 플래그를 사용했을 때 검색한 데이터에서 true 가 나타나면 안된다.
    private boolean deleted;


    public void addBookAndAuthors(BookAndAuthor... bookAndAuthors) {
        Collections.addAll(this.bookAndAuthors, bookAndAuthors);
    }

    // User

    // 즉 User 와 Order 1대 N 관계로 처리
    // user_products 중간 테이블 생성 => Order 라는 또 다른 entity 로 처리할 수 있다.

    // Order 와 Product 는 M대 1 관계로 처리
    // Product
}
