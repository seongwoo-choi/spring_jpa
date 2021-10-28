package com.example.bookmanager.service;

import com.example.bookmanager.domain.Author;
import com.example.bookmanager.domain.Book;
import com.example.bookmanager.repository.AuthorRepository;
import com.example.bookmanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    // 의존성 주입
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    public void putBookAndAuthor() throws Exception {
        Book book = new Book();
        book.setName("JPA 시작");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("martin");

        authorRepository.save(author);

        throw new Exception("오류가 나서 DB commit 이 발생하지 않습니다.");
    }

}
