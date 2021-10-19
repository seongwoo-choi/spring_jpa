package com.example.bookmanager.repository;

import com.example.bookmanager.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

// Spring context 를 활용해서 Test 를 하겠다.
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void crud() {
        userRepository.save(new User(1, "a", "a@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        userRepository.save(new User(2, "b", "b@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        userRepository.save(new User(3, "c", "c@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        userRepository.save(new User(4, "d", "d@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        userRepository.save(new User(5, "e", "e@google.co.kr", LocalDateTime.now(), LocalDateTime.now()));
        userRepository.save(new User(6, "f", "f@google.co.kr", LocalDateTime.now(), LocalDateTime.now()));


//        // 내림 차순으로 정렬
//        List<User> users1 = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
//
//        // id 가 1, 3, 5 인 entity select 해서 가져오겠다.
//        List<User> users2 = userRepository.findAllById(Lists.newArrayList(1L, 3L, 5L));
//
//        User user1 = new User("Jack", "Jack@naver.com");
//        User user2 = new User("Ack", "Ack@naver.com");
//
//        // 이터레이블 한 값을 모두 저장한다.
//        userRepository.saveAll(Lists.newArrayList(user1, user2));
//
//        // 엔티티 하나만 저장
//        userRepository.save(user1);
//
//        List<User> users3 = userRepository.findAll();

//        User user = userRepository.getOne(1L);
//        System.out.println(user);

//        // 1L 이 존재하지 않으면 값을 null 로 바꾼다.
//        User user = userRepository.findById(1L).orElse(null);
//        System.out.println(user);

//        userRepository.saveAndFlush(new User("csw", "csw"));
//

//        long count = userRepository.count();
//
//        boolean exists = userRepository.existsById(1L);
//
//        System.out.println(exists);

//        // Entity 에 null 이 들어가면 안된다.
//        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
//

//        // deleteInBatch => entity 가 있든 없든 일단 삭제함
//        userRepository.deleteInBatch(userRepository.findAllById(Lists.newArrayList(1L, 3L)));

//        userRepository.deleteAllInBatch();

//        // Pagination 을 위한 Page 객체, PageRequest
//        Page<User> users = userRepository.findAll(PageRequest.of(0, 3));
//
//        userRepository.findAll().forEach(System.out::println);
//
//        System.out.println("page : "+users);
//        System.out.println("totalElements : "+users.getTotalElements());
//        System.out.println("totalPages : "+users.getTotalPages());
//        System.out.println("numberOfElements : "+users.getNumberOfElements());
//        System.out.println("sort : "+users.getSort());
//        System.out.println("size : "+users.getSize());
//
//        users.getContent().forEach(System.out::println);


//        User user = new User();
//        user.setEmail("naver");
//
//        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("email", contains());
//        Example<User> example = Example.of(user, matcher);
//
//        userRepository.findAll(example).forEach(System.out::println);

//        users.forEach(System.out::println);

//        userRepository.save(new User("david", "david@naver.com"));
//
//        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
//        user.setEmail("how0326@naver.com");
//
//        // id 가 이미 있는 녀석일 경우 update
//        // 상황에 따라 insert 와 update 를 둘 다 사용한다.
//        userRepository.save(user);

    }

    @Test
    void select() {
        userRepository.save(new User(1, "a", "a@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        userRepository.save(new User(2, "b", "b@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        userRepository.save(new User(3, "c", "c@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        userRepository.save(new User(4, "d", "d@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        userRepository.save(new User(5, "e", "e@google.co.kr", LocalDateTime.now(), LocalDateTime.now()));
        userRepository.save(new User(6, "a", "a@google.co.kr", LocalDateTime.now(), LocalDateTime.now()));

        System.out.println("findByEmail :" + userRepository.findByEmail("a@naver.com"));
        System.out.println("getByEmail :" + userRepository.getByEmail("a@naver.com"));
        System.out.println("readByEmail :" + userRepository.readByEmail("a@naver.com"));
        System.out.println("queryByEmail :" + userRepository.queryByEmail("a@naver.com"));
        System.out.println("searchByEmail :" + userRepository.searchByEmail("a@naver.com"));
        System.out.println("streamByEmail :" + userRepository.streamByEmail("a@naver.com"));
        System.out.println("findUserByEmail :" + userRepository.findUserByEmail("a@naver.com")); // findUserByEmail 에서 User 는 구현체에서 무시된다.

        System.out.println("findFirst1ByName :" + userRepository.findFirst1ByName("a"));
        System.out.println("findTop1ByName :" + userRepository.findTop1ByName("a"));
        System.out.println("findLast1ByName :" + userRepository.findLast1ByName("a"));

        System.out.println("findByEmailAndName :" + userRepository.findByEmailAndName("a@naver.com", "a"));
        System.out.println("findByEmailOrName :" + userRepository.findByEmailOrName("a@naver.com", "b"));




    }
}