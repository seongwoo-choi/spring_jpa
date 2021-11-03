package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 테이블은 연관 관계의 데이터들을 inner join 으로 불러오기 때문에 문제가 발생한다.
    // N+1 문제 해결법

    // JPQL => Review 엔티티의 값을 사용한다.
    @Query(value = "select DISTINCT r from Review r join fetch r.comments")
    List<Review> findByFetchJoin();

    // comments 에 대해서 엔티티 그래프를 그리겠다.
    @EntityGraph(attributePaths = "comments")
    @Query("select r from Review r")
    List<Review> findAllByEntityGraph();

    // 위의 엔티티 그래프 쿼리와 동일하다.
    @EntityGraph(attributePaths = "comments")
    List<Review> findAll();
}
