package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @Transactional
    void reviewTest() {
//        List<Review> reviews = reviewRepository.findAll();

        // fetch join 사용하는 방법
        List<Review> reviews = reviewRepository.findByFetchJoin();

        // entity graph 사용하는 방법
        List<Review> reviews1 = reviewRepository.findAllByEntityGraph();

        List<Review> reviews2 = reviewRepository.findAll();

//        System.out.println(reviews);
//        System.out.println("전체를 가져오기");
//
//        System.out.println(reviews.get(0).getComments());
//        System.out.println("첫 번째 리뷰의 커멘트들을 가져오기");
//
//        System.out.println(reviews.get(1).getComments());
//        System.out.println("두 번째 리뷰의 커멘트들을 가죠오기");

        reviews.forEach(System.out::println);
        reviews1.forEach(System.out::println);
        reviews2.forEach(System.out::println);
    }
}
