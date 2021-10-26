package com.example.bookmanager.domain;

import com.example.bookmanager.domain.listener.Auditable;
import com.example.bookmanager.domain.listener.UserEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
// @NonNull 어노테이션을 사용하기 위해 작성
@RequiredArgsConstructor
@Builder
@Entity
@EntityListeners(value = { AuditingEntityListener.class, UserEntityListener.class })
// index 나 제약사항은 db 에 맡기고 Entity 에는 적용시키지 않는게 보편적이다.
@Table(name = "user", indexes = { @Index(columnList = "name")}, uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
// BaseEntity 의 @MappedSuperclass 로 인해 내부변수들을 User Entity 의 컬럼으로 사용
public class User extends BaseEntity {

    // User 라는 테이블의 pk, 1 씩 자동으로 증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Enum 을 사용하는 경우 EnumType.STRING 으로 해줘야 Enum 값이 추가될 때 오류가 발생하지 않는다.
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @NonNull
    private String name;

    @NonNull
    @Column(unique = true) // 이 컬럼의 값은 유일해야 한다.
    private String email;


    // userHistory 외래키
    // OneToMany 에서 참조하는 값은 One 에 해당하는 PK 아이디를 Many 쪽에서 외래키로 갖게 된다.
    // 그래서 일반적인 상황에선 ManyToOne 이 깔끔하다.
    @OneToMany(fetch = FetchType.EAGER)
    // Entity 가 어떤 컬럼으로 join 을 할 지 정해주는 어노테이션
    // 생성과 업데이트를 할 수 없는 컬럼, 오로지 읽기만 가능하다.
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    // NullPointException 이 발생하지 않도록 빈 배열 값 넣어준다.
    @ToString.Exclude
    private List<UserHistory> userHistories = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();
}
