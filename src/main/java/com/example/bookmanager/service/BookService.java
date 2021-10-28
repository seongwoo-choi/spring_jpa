package com.example.bookmanager.service;

import com.example.bookmanager.domain.Author;
import com.example.bookmanager.domain.Book;
import com.example.bookmanager.repository.AuthorRepository;
import com.example.bookmanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class BookService {

    // 의존성 주입
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final EntityManager entityManager;


    // rollbackFor = Exception.class 을 사용하면 Exception 이 CheckedException 임에도 불구하고 rollback 이 되게 된다.
    @Transactional()
    public void putBookAndAuthor() {
        Book book = new Book();
        book.setName("JPA 시작");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("martin");

        authorRepository.save(author);

        throw new RuntimeException("오류가 나서 DB commit 이 발생하지 않습니다.");
    }

    // READ_UNCOMMITTED => 커밋되지 않는(트랜잭션 처리중인) 데이터에 대한 읽기를 허용, dirty read 문제와 데이터 정합성 문제
    // SERIALIZABLE 커밋이 일어나지 않은 트랜잭션이 존재하면 락을 통해서 waiting, 커밋이 실행되어야 추가적인 로직이 실행
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void get(Long id) {
        System.out.println(">>>> " + bookRepository.findById(id));
        System.out.println(">>>> " + bookRepository.findAll());

        entityManager.clear();

        // 두 번 째 findById 이기 때문에 영속성 컨텍스트에 캐시되어있다. => entityManger 에서 캐싱을 정리해줘야 한다.
        System.out.println(">>>> " + bookRepository.findById(id));
        System.out.println(">>>> " + bookRepository.findAll());

        bookRepository.update();

        entityManager.clear();

//        Book book = bookRepository.findById(1L).get();
//        book.setName("바뀔까?");
//        bookRepository.save(book);

    }

}
