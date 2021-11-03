package com.example.bookmanager.service;

import com.example.bookmanager.domain.Comment;
import com.example.bookmanager.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void init() {
        for (int i = 0; i < 10; i++) {
            Comment comment = new Comment();
            comment.setComment("너가 최고야 " + i);

            commentRepository.save(comment);
        }
    }

    @Transactional(readOnly = true)
    public void updateSomething() {
        List<Comment> comments = commentRepository.findAll();

        for (Comment comment : comments) {
            comment.setComment("별로");
            // 영속화 => 영속성 컨텍스트에서 관리
            commentRepository.save(comment);
        }
    }

    @Transactional
    public void insertSomething() {
        Comment comment = new Comment();
        comment.setComment("이건 뭐에요");

        // 영속화
        commentRepository.save(comment);
    }
}
