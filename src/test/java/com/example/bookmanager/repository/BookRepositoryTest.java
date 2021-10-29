package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Book;
import com.example.bookmanager.domain.Publisher;
import com.example.bookmanager.domain.Review;
import com.example.bookmanager.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    @Transactional
    void bookRelationTest() {
        givenBookAndReview();


        User user = userRepository.findByEmail("csw@naver.com");

        System.out.println("Review : " + user.getReviews());
        // book 에 대한 정보를 출력 가능
        System.out.println("Book : " + user.getReviews().get(0).getBook());
        System.out.println("Publisher : " + user.getReviews().get(0).getBook().getPublisher());
    }

    @Transactional
    @Test
    void bookCascadeTest() {
        Book book = new Book();
        book.setName("JPA 책1");

//        bookRepository.save(book);

        Publisher publisher = new Publisher();
        publisher.setName("csw");

//        publisherRepository.save(publisher);

        // 아래와 같은 방법보다 더 좋은 방법이다.
        book.setPublisher(publisher);
        bookRepository.save(book);

        // 퍼블리셔에 연관관계를 줄 때 setter 로 주지 않고 getter 로 값을 받아서 연관관계를 설정해 주었다.
        // 오브젝트는 주솟값을 활용해서 처리가 되기 때문에 getBooks 에 어떤 값을 추가할 때 publisher 가 가지고 있는 book 의 정보도 변경된다.
        // publisher.getBooks().add(book);
//        publisher.addBook(book);
//        publisherRepository.save(publisher);

        System.out.println("books : " + bookRepository.findAll());
        System.out.println("publishers : " + publisherRepository.findAll());


        Book book1 = bookRepository.findById(1L).get();
        // 데이터가 update 되는 경우 => CASCADE 를 통해 자동으로 publisher 가 업데이트 될 수 있도록 할 수 있다.
        book1.getPublisher().setName("CSW");

        bookRepository.save(book1);
        System.out.println("publisher : " + publisherRepository.findAll());

        Book book2 = bookRepository.findById(1L).get();
//        bookRepository.delete(book2);
        // bookRepository.deleteById(1L);

        Book book3 = bookRepository.findById(1L).get();
        book3.setPublisher(null);

        bookRepository.save(book3);

        System.out.println("books : " + bookRepository.findAll());
        System.out.println("publishers : " + publisherRepository.findAll());
        // 연관관계는 삭제됐지만 그 값 자체는 DB 에 남아있다.
        System.out.println("book3-publisher : " + bookRepository.findById(1L).get().getPublisher());

    }

    @Test
    void bookRemoveCascadeTest() {
        bookRepository.deleteById(1L);
        System.out.println("books : " + bookRepository.findAll());
        System.out.println("publishers : " + publisherRepository.findAll());

        bookRepository.findAll().forEach(book -> System.out.println(book.getPublisher()));
    }

    @Test
    void softDelete() {
        bookRepository.findAll().forEach(System.out::println);
        System.out.println(bookRepository.findById(1L));

        bookRepository.findByCategoryIsNull().forEach(System.out::println);

        bookRepository.findAllByDeletedFalse().forEach(System.out::println);
        bookRepository.findByCategoryIsNullAndDeletedFalse().forEach(System.out::println);

    }

    @Test
    void queryTest() {
        Book book = new Book();
        book.setName("csw");
        book.setId(1L);

        Book book1 = new Book();
        book1.setName("csw2");
        book1.setId(2L);

        Book book2 = new Book();
        book2.setId(3L);
        book2.setName("csw3");

        bookRepository.save(book);
        bookRepository.save(book1);
        bookRepository.save(book2);

        bookRepository.findAll().forEach(System.out::println);
        System.out.println("findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual : "
                + bookRepository.findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(
                "csw",
                LocalDateTime.now().minusDays(1L),
                LocalDateTime.now().minusDays(1L)
        ));

        System.out.println("findByNameRecently : " +
                bookRepository.findByNameRecently("csw",
                        LocalDateTime.now().minusDays(1L),
                        LocalDateTime.now().minusDays(1L)));

        System.out.println(bookRepository.findBookNameAndCategory());

        bookRepository.findBookNameAndCategory().forEach(b -> {
            System.out.println(b.getName() + " : " + b.getCategory());
        });

        bookRepository.findBookNameAndCategory(PageRequest.of(1, 1)).forEach(
                bookNameAndCategory -> System.out.println(bookNameAndCategory.getName() + " : " + bookNameAndCategory.getCategory()));

        bookRepository.findBookNameAndCategory(PageRequest.of(0, 1)).forEach(
                bookNameAndCategory -> System.out.println(bookNameAndCategory.getName() + " : " + bookNameAndCategory.getCategory()));
    }

    @Test
    void nativeQueryTest() {
        Book book1 = new Book();
        book1.setName("csw");
        book1.setId(1L);

        Book book2 = new Book();
        book2.setName("csw2");
        book2.setId(2L);

        Book book3 = new Book();
        book3.setId(3L);
        book3.setName("csw3");

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

//        bookRepository.findAll().forEach(System.out::println);
//        bookRepository.findAllCustom().forEach(System.out::println);
        List<Book> books = bookRepository.findAll();

        for (Book book : books) {
            book.setCategory("IT 전문서");
        }

        bookRepository.saveAll(books);

        System.out.println(bookRepository.findAll());

        System.out.println("affected rows : " + bookRepository.updateCategories());
        bookRepository.findAllCustom().forEach(System.out::println);

        System.out.println(bookRepository.showTables());
    }

    private void givenBookAndReview() {
        givenReview(givenUser(), givenBook(givenPublisher()));
    }

    private Publisher givenPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("무슨 책 출판사");

        return publisherRepository.save(publisher);
    }

    private Book givenBook(Publisher publisher) {
        Book book = new Book();
        book.setName("무슨 책");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private User givenUser() {
        User user = new User();
        user.setName("csw");
        user.setEmail("csw@naver.com");
        userRepository.save(user);

        return userRepository.findByEmail("csw@naver.com");
    }

    private void givenReview(User user, Book book) {
        Review review = new Review();

        review.setTitle("remember");
        review.setContent("좋은 책");
        review.setScore(4.0f);
        review.setUser(user);
        review.setBook(book);

        reviewRepository.save(review);
    }
}
