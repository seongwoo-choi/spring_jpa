package com.example.bookmanager.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
// index 나 제약사항은 db 에 맡기고 Entity 에는 적용시키지 않는게 보편적이다.
//@Table(name = "user", indexes = { @Index(columnList = "name")}, uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {

    // User 라는 테이블의 pk, 1 씩 자동으로 증가
    @Id
    @GeneratedValue
    private long id;

    // Enum 을 사용하는 경우 EnumType.STRING 으로 해줘야 Enum 값이 추가될 때 오류가 발생하지 않는다.
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @NonNull
    private String name;

    @NonNull
    @Column(unique = true) // 이 컬럼의 값은 유일해야 한다.
    private String email;

    // 데이터베이스에 crtdt 라고 구현되고 Entity 의 createdAt 과 맵핑된다.
    // nullable=false 컬럼에 null 이 올 수 없다. 즉, createdAt 에는 반드시 값이 존재해야 한다.
//    @Column(name = "crtdat", nullable = false)
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

//    // DB 에 적용시키지 않고 서버 내에서 사용하고 싶을 경우 => @Transient 사용
//    @Transient
//    private String testData;

}
