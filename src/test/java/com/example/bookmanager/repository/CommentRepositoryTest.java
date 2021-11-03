package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    void commentTest() {
        Comment comment = new Comment();
        comment.setComment("별로");
        comment.setCommentedAt(LocalDateTime.now());

        commentRepository.save(comment);

        entityManager.clear();

//        try {
//            System.out.println(commentRepository.findById(1L).orElseThrow(null));
//        } catch (Exception e) {
//            System.out.println("null 에러");
//        }

        System.out.println(comment);

        commentRepository.findAll().forEach(System.out::println);
    }
}