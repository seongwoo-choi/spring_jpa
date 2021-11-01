package com.example.bookmanager.service;

import com.example.bookmanager.domain.Author;
import com.example.bookmanager.domain.Book;
import com.example.bookmanager.repository.AuthorRepository;
import com.example.bookmanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    // 의존성 주입
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final EntityManager entityManager;
    private final AuthorService authorService;


    // rollbackFor = Exception.class 을 사용하면 Exception 이 CheckedException 임에도 불구하고 rollback 이 되게 된다.
    // propagation = Propagation.REQUIRED 디폴트 값, 트랜잭션을 재활용한다.
    // propagation = Propagation.REQUIRES_NEW, 트랜잭션이 있던 없던 새로운 트랜잭션을 생성.
    // propagation = Propagation.NESTED, 별도의 트랜잭션을 생성하는 것이 아니라, 하나의 트랜잭션이지만 개별적으로 독립적으로 움직일 수 있는 녀석
    @Transactional(propagation = Propagation.REQUIRED)
    public void putBookAndAuthor() {
        Book book = new Book();
        book.setName("JPA 시작");

        bookRepository.save(book);

        // 호출한 트랜잭션이 오류가 발생하게 되면 author 는 롤백이 일어나게 되는데 기존에 실행했던 save 에 대해선 커밋이 일어난다.
        // NESTED 자체에 요 내에서 처리하도록 정리되어 있기 때문에 book 에는 영향을 끼치지 않는다.
        // 만약 book 에서 오류가 발생하게 되면 book 과 author 모두 롤백된다.
        // 왜냐하면 동일한 트랜잭션을 사용하고 있기 때문이다.
        try {
            authorService.putAuthor();
        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
        }

        throw new RuntimeException("오류가 발생하였습니다. transaction 은 어떻게 될까요?");

//        Author author = new Author();
//        author.setName("martin");
//
//        authorRepository.save(author);

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


    @Transactional
    public List<Book> getAll() {
        List<Book> books = bookRepository.findAll();

        books.forEach(System.out::println);

        return books;
    }
}
