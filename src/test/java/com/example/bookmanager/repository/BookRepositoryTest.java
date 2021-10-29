package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Book;
import com.example.bookmanager.domain.Publisher;
import com.example.bookmanager.domain.Review;
import com.example.bookmanager.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


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
