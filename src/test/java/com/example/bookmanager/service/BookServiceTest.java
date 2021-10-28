package com.example.bookmanager.service;

import com.example.bookmanager.repository.AuthorRepository;
import com.example.bookmanager.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void transactionTest() {
        try {
            bookService.putBookAndAuthor();
        } catch (Exception e) {
            System.out.println(">>> " + e.getMessage());
        }

        System.out.println("books : " + bookRepository.findAll());
        System.out.println("authors : " + authorRepository.findAll());
    }
}