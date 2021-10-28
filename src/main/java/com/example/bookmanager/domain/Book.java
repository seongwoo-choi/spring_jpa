package com.example.bookmanager.domain;

import com.example.bookmanager.domain.listener.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate // => READ_UNCOMITTIED 에서 데이터가 업데이트 된 이후 롤백되는 케이스에서 데이터 정합성을 해친다. 그것을 막기 위해 사용
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String category;

    @ManyToOne
    @ToString.Exclude
    private Publisher publisher;

    @ManyToOne
    @ToString.Exclude
    private Author author;

    // Entity 릴레이션을 사용하는 경우에 특히 ToString 메서드는 순환참조가 걸리게 된다.
    // 그래서 특별히 필요한 경우를 제외하면 릴레이션은 단방향을 걸거나 ToString 에서 제외하는 처리가 필요하다.
    // 여기선 ToString.Exclude 로 ToString 에서 제거한다.
    // 연관키를 해당 테이블에서 더 이상 가지지 않는다.
    @OneToOne(mappedBy = "book")
    @ToString.Exclude
    private BookReviewInfo bookReviewInfo;

    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

//    @ManyToMany
    @OneToMany
    @JoinColumn(name="book_id")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

    public void addBookAndAuthor(BookAndAuthor... bookAndAuthors) {
        Collections.addAll(this.bookAndAuthors, bookAndAuthors);
    }

    // User

    // 즉 User 와 Order 1대 N 관계로 처리
    // user_products 중간 테이블 생성 => Order 라는 또 다른 entity 로 처리할 수 있다.

    // Order 와 Product 는 M대 1 관계로 처리
    // Product
}
