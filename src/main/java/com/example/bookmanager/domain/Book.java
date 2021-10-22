package com.example.bookmanager.domain;

import com.example.bookmanager.domain.listener.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
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
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String category;

    @ManyToOne
    @ToString.Exclude
    private Publisher publisher;

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

    @ManyToMany
    @ToString.Exclude
    private List<Author> authors;

    public void addAuthor(Author... author) {
        Collections.addAll(this.authors, author);
    }
}
