package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Book;
import com.example.bookmanager.repository.dto.BookNameAndCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.time.LocalDateTime;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Modifying
    @Query(value = "update book set category='none'", nativeQuery = true)
    void update();

    List<Book> findByCategoryIsNull();

    List<Book> findAllByDeletedFalse();

    List<Book> findByCategoryIsNullAndDeletedFalse();

    // 카테고리가 Null 이면서 이름은 입력받은 이름과 같고 입력받은 createdAt 보다 크거나 같고 입력받은 updatedAt 보다 크거나 같은 값을 리턴
    List<Book> findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(String name, LocalDateTime createdAt, LocalDateTime updatedAt);

    @Query(value = "select b from Book b "
            + "where name =?1 and createdAt >= ?2 and updatedAt >= ?3")
    List<Book> findByNameRecently(String name, LocalDateTime createdAt, LocalDateTime updatedAt);

    @Query(value = "select b from Book b "
            + "where name = :name and createdAt >= :createdAt and updatedAt >= :updatedAt")
    List<Book> findByNameRecently2(
            @Param("name") String name,
            @Param("createdAt") LocalDateTime createdAt,
            @Param("updatedAt") LocalDateTime updatedAt);

    // 이름과 카테고리만 추출하는 객체
    @Query(value = "select new com.example.bookmanager.repository.dto.BookNameAndCategory(b.name, b.category) from Book b")
    List<BookNameAndCategory> findBookNameAndCategory();

    @Query(value = "select new com.example.bookmanager.repository.dto.BookNameAndCategory(b.name, b.category) from Book b")
    Page<BookNameAndCategory> findBookNameAndCategory(Pageable pageable);

    @Query(value = "select * from book", nativeQuery = true)
    List<Book> findAllCustom();

    // @Modifying => update 라는 것을 표현
    @Modifying
    @Transactional
    @Query(value = "update book set category = 'IT 전문서'", nativeQuery = true)
    int updateCategories();

    // 일반적인 jpql 쿼리로는 show tables 를 실행할 수 없다.
    // 그러나 native 쿼리를 사용하면 show tables 를 실행하여 현재 table 목록을 확인할 수 있다.
    @Query(value = "show tables", nativeQuery = true)
    List<String> showTables();
}
