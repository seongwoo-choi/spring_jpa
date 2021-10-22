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
//@Table(name = "user", indexes = { @Index(columnList = "name")}, uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
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

    @OneToMany(fetch = FetchType.EAGER)
    // Entity 가 어떤 컬럼으로 join 을 할 지 정해주는 어노테이션
    // 생성과 업데이트를 할 수 없는 컬럼, 오로지 읽기만 가능하다.
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    // NullPointException 이 발생하지 않도록 빈 배열 값 넣어준다.
    private List<UserHistory> userHistoryList = new ArrayList<>();



    // 데이터베이스에 crtdt 라고 구현되고 Entity 의 createdAt 과 맵핑된다.
    // nullable=false 컬럼에 null 이 올 수 없다. 즉, createdAt 에는 반드시 값이 존재해야 한다.
//    @Column(name = "crtdat", nullable = false)
//    @Column(updatable = false)
//    @CreatedDate // @PrePersist Auditing 하기 위한 어노테이션(AuditingEntityListener)
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate // @PreUpdate Auditing 하기 위한 어노테이션(AuditingEntityListener)
//    private LocalDateTime updatedAt;


//    // 개발자가 setCreatedAt / setUpdatedAt 을 하지않아도 자동으로 세팅이 된다.
//    @PrePersist
//    public void prePersist() {
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        this.updatedAt = LocalDateTime.now();
//    }

//    // DB 에 적용시키지 않고 서버 내에서 사용하고 싶을 경우 => @Transient 사용
//    @Transient
//    private String testData;

//    @PrePersist // persist 인서트 전에 실행되는 메소드
//    public void prePersist() {
//        System.out.println(">>>> prePersist");
//    }
//
//    @PostPersist // persist 메서드 실행되고 난 후 실행되는 메서드
//    public void postPersist() {
//        System.out.println(">>>> postPersist");
//    }
//
//    @PreUpdate // merge 메서드가 실행되기 전에 실행되는 메서드
//    public void preUpdate() {
//        System.out.println(">>>> preUpdate");
//    }
//
//    @PostUpdate // merge 메서드가 실행되고 후 실행되는 메서드
//    public void postUpdate() {
//        System.out.println(">>>> postUpdate");
//    }
//
//    @PreRemove // delete 메서드가 실행되기 전에 실행되는 메서드
//    public void preRemove() {
//        System.out.println(">>>> preRemove");
//    }
//
//    @PostRemove // delete 메서드가 실행되고 난 후 실행되는 메서드
//    public void postRemove() {
//        System.out.println(">>>> postRemove");
//    }
//
//    @PostLoad // select 조회가 실행되고 난 후 실행되는 메서드
//    public void postLoad() {
//        System.out.println(">>>> postLoad");
//    }



}
