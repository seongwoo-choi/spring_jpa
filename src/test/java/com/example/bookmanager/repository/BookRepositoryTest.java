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

        System.out.println("Review : "+ user.getReviews());
        // book 에 대한 정보를 출력 가능
        System.out.println("Book : "+ user.getReviews().get(0).getBook());
        System.out.println("Publisher : "+user.getReviews().get(0).getBook().getPublisher());
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
