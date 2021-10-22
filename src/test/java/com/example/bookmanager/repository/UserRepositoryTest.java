package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Gender;
import com.example.bookmanager.domain.User;
import com.example.bookmanager.domain.UserHistory;
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

    @Autowired
    private UserHistoryRepository userHistoryRepository;

//    @Test
//    @Transactional
//    void crud() {
//        userRepository.save(new User(1, "a", "a@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        userRepository.save(new User(2, "b", "b@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        userRepository.save(new User(3, "c", "c@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        userRepository.save(new User(4, "d", "d@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        userRepository.save(new User(5, "e", "e@google.co.kr", LocalDateTime.now(), LocalDateTime.now()));
//        userRepository.save(new User(6, "f", "f@google.co.kr", LocalDateTime.now(), LocalDateTime.now()));
//
//
////        // 내림 차순으로 정렬
////        List<User> users1 = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
////
////        // id 가 1, 3, 5 인 entity select 해서 가져오겠다.
////        List<User> users2 = userRepository.findAllById(Lists.newArrayList(1L, 3L, 5L));
////
////        User user1 = new User("Jack", "Jack@naver.com");
////        User user2 = new User("Ack", "Ack@naver.com");
////
////        // 이터레이블 한 값을 모두 저장한다.
////        userRepository.saveAll(Lists.newArrayList(user1, user2));
////
////        // 엔티티 하나만 저장
////        userRepository.save(user1);
////
////        List<User> users3 = userRepository.findAll();
//
////        User user = userRepository.getOne(1L);
////        System.out.println(user);
//
////        // 1L 이 존재하지 않으면 값을 null 로 바꾼다.
////        User user = userRepository.findById(1L).orElse(null);
////        System.out.println(user);
//
////        userRepository.saveAndFlush(new User("csw", "csw"));
////
//
////        long count = userRepository.count();
////
////        boolean exists = userRepository.existsById(1L);
////
////        System.out.println(exists);
//
////        // Entity 에 null 이 들어가면 안된다.
////        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
////
//
////        // deleteInBatch => entity 가 있든 없든 일단 삭제함
////        userRepository.deleteInBatch(userRepository.findAllById(Lists.newArrayList(1L, 3L)));
//
////        userRepository.deleteAllInBatch();
//
////        // Pagination 을 위한 Page 객체, PageRequest
////        Page<User> users = userRepository.findAll(PageRequest.of(0, 3));
////
////        userRepository.findAll().forEach(System.out::println);
////
////        System.out.println("page : "+users);
////        System.out.println("totalElements : "+users.getTotalElements());
////        System.out.println("totalPages : "+users.getTotalPages());
////        System.out.println("numberOfElements : "+users.getNumberOfElements());
////        System.out.println("sort : "+users.getSort());
////        System.out.println("size : "+users.getSize());
////
////        users.getContent().forEach(System.out::println);
//
//
////        User user = new User();
////        user.setEmail("naver");
////
////        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("email", contains());
////        Example<User> example = Example.of(user, matcher);
////
////        userRepository.findAll(example).forEach(System.out::println);
//
////        users.forEach(System.out::println);
//
////        userRepository.save(new User("david", "david@naver.com"));
////
////        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
////        user.setEmail("how0326@naver.com");
////
////        // id 가 이미 있는 녀석일 경우 update
////        // 상황에 따라 insert 와 update 를 둘 다 사용한다.
////        userRepository.save(user);
//
//    }
//
//    @Test
//    void select() {
//        userRepository.save(new User(1, "abb", "a@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        userRepository.save(new User(2, "bab", "b@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        userRepository.save(new User(3, "caa", "c@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        userRepository.save(new User(4, "dbca", "d@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        userRepository.save(new User(5, "ecb", "e@google.co.kr", LocalDateTime.now(), LocalDateTime.now()));
//        userRepository.save(new User(6, "fca", "f@google.co.kr", LocalDateTime.now(), LocalDateTime.now()));
//
//        // select * from User where User.email = ?
//        System.out.println("findByEmail :" + userRepository.findByEmail("a@naver.com"));
//
//        // select * from User where User.email = ?
//        System.out.println("getByEmail :" + userRepository.getByEmail("a@naver.com"));
//
//        // select * from User where User.email = ?
//        System.out.println("readByEmail :" + userRepository.readByEmail("a@naver.com"));
//
//        // select * from User where User.email = ?
//        System.out.println("queryByEmail :" + userRepository.queryByEmail("a@naver.com"));
//
//        // select * from User where User.email = ?
//        System.out.println("searchByEmail :" + userRepository.searchByEmail("a@naver.com"));
//
//        // select * from User where User.email = ?
//        System.out.println("streamByEmail :" + userRepository.streamByEmail("a@naver.com"));
//
//        // select * from User where User.email = ?
//        System.out.println("findUserByEmail :" + userRepository.findUserByEmail("a@naver.com")); // findUserByEmail 에서 User 는 구현체에서 무시된다.
//
//        // select * from User where User.name = a limit 1
//        System.out.println("findFirst1ByName :" + userRepository.findFirst1ByName("a"));
//
//        // select * from User where User.name = a limit 1
//        System.out.println("findTop1ByName :" + userRepository.findTop1ByName("a"));
//
//        // select * from User where User.name = ?      => jpa 가 인식하지 못함
//        System.out.println("findLast1ByName :" + userRepository.findLast1ByName("a"));
//
//        // select * from User where User.name = ? and User.email = ?
//        System.out.println("findByEmailAndName :" + userRepository.findByEmailAndName("a@naver.com", "a"));
//
//        // select * from User where User.email = ? or User.name = ?
//        System.out.println("findByEmailOrName :" + userRepository.findByEmailOrName("a@naver.com", "b"));
//
//        // select * from User where User.id > ?
//        System.out.println("findByIdAfter :" + userRepository.findByIdAfter(1L));
//
//        // select * from User where User.createdAt > ?
//        System.out.println("findByCreatedAtGreaterThan :" + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
//
//        // select * from User where User.createdAt >= ?
//        System.out.println("findByCreatedAtGreaterThanEqual :" + userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));
//
//        // select * from User where User.createdAt between ? and ?
//        System.out.println("findByCreatedAtBetween :" + userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now()));
//
//        // select * from User where User.id between ? and ?    => 1L 과 3L 끝 값을 포함한 값
//        System.out.println("findByIdBetween :" + userRepository.findByIdBetween(1L, 3L));
//
//        // select * from User where User.id >= ? and User.id <= ?    => between 과 같다.
//        System.out.println("findByIdGreaterThanEqualAndIdLessThanEqual :" + userRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1L, 3L));
//
//        // select * from User where User.id is not null
//        System.out.println("findByIdIsNotNull :" + userRepository.findByIdIsNotNull());
//
//        // String 에서 empty 는 "" 빈 문자열을 뜻 함. 여기선 컬렉션 타입의 empty 를 체크함
//        // System.out.println("findByAddressesIsNotEmpty :" + userRepository.findByAddressesIsNotEmpty());
//
//        // in 절은 보통 다른 쿼리의 return 된 결과 값을 넣는다.
//        // select * from User where User.name in (?, ?)
//        System.out.println("findByNameIn :" + userRepository.findByNameIn(Lists.newArrayList("a", "b")));
//
//        // select * from User where User.name like ?% escape ?
//        System.out.println("findByNameStartingWith :" + userRepository.findByNameStartingWith("a"));
//
//        // select * from User where User.name like %? escape ?
//        System.out.println("findByNameEndingWith :" + userRepository.findByNameEndingWith("b"));
//
//        // select * from User where User.name like %?% escape ?
//        System.out.println("findByNameContains :" + userRepository.findByNameContains("c"));
//
//        // select * from User where User.name lik %? escape ?
//        System.out.println("findByNameLike :" + userRepository.findByNameLike("%ab"));
//    }
//
//    @Test
//    void pagingAndSortingTest() {
//        userRepository.save(new User(1, "abb", "a@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        userRepository.save(new User(2, "bab", "b@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        userRepository.save(new User(3, "caa", "c@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        userRepository.save(new User(4, "dbca", "d@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        userRepository.save(new User(5, "ecb", "e@google.co.kr", LocalDateTime.now(), LocalDateTime.now()));
//        userRepository.save(new User(6, "fca", "f@google.co.kr", LocalDateTime.now(), LocalDateTime.now()));
//        userRepository.save(new User(7, "abb", "f@google.co.kr", LocalDateTime.now(), LocalDateTime.now()));
//
//        System.out.println("findTop1ByName :" + userRepository.findTop1ByName("abb"));
//
//        // findLast1ByName => JPA 가 인식하지 못함, 수정해야 한다.
//        System.out.println("findLast1ByName :" + userRepository.findLast1ByName("abb"));
//
//        // select * from User where User.name = ? order by User.id desc limit ?
//        System.out.println("findTop1ByNameOrderByIdDesc :" + userRepository.findTop1ByNameOrderByIdDesc("abb"));
//
//        // select * from User where User.name = ? order by User.id desc, User.email asc limit ?
//        System.out.println("findFirst1ByNameOrderByIdDescEmailAsc :" + userRepository.findFirst1ByNameOrderByIdDescEmailAsc("abb"));
//
//        // select * from User where User.name = ? order by User.id desc, User.name asc limit ?
//        System.out.println("findFirstByNameWithSortParams :" + userRepository.findFirstByName("abb", Sort.by(Sort.Order.desc("id"), Sort.Order.asc("name"))));
//
//        System.out.println("findFirstByNameWithSortParams :" + userRepository.findFirstByName("abb", getSort()));
//
//        // select * from User where User.name = ? order by User.id desc limit ?, select count(User.id) from User where User.name = ?  ==> 페이지 내부에 총 몇개가 존재하는지 확인하기 위해 쿼리문이 작동
//        System.out.println("findByNameWithPaging :" + userRepository.findByName("abb", PageRequest.of(1, 1, Sort.by(Sort.Order.desc("id")))).getContent());
//    }

    @Test
    void insertAndUpdateTest() {
        User user = new User();
        user.setName("csw");
        user.setEmail("csw@naver.com");

        userRepository.save(user);

        // findById ==> Optional 이기 때문에 orElseThrow 를 던지거나 Optional 로 형변환 해줘야 한다.
        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("cswwwwwww");

        userRepository.save(user2);
    }

//    @Test
//    void enumTest() {
//        userRepository.save(new User(10, null,"a", "a@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
//
//        user.setGender(Gender.MALE);
//
//        // user update
//        userRepository.save(user);
//
//        userRepository.findAll().forEach(System.out::println);
//
//        System.out.println(userRepository.findRawRecord().get("gender"));
//    }

    @Test
    void listenerTest() {
        User user = new User();
        user.setEmail("csw@naver.com");
        user.setName("csw");

        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("cswwwww");

        userRepository.save(user2);

        userRepository.deleteById(1L);
    }

    @Test
    void prePersistTest() {
        User user = new User();
        user.setEmail("csw@naver.com");
        user.setName("csw");
//        user.setCreatedAt(LocalDateTime.now());
//        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        System.out.println(userRepository.findByEmail("csw@naver.com"));
    }

    @Test
    void preUpdateTest() {
        User user = new User();
        user.setEmail("csw@naver.com");
        user.setName("csw");
        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        System.out.println("as-is :"+user2);

        user2.setName("choi");
        userRepository.save(user2);

        System.out.println("to-be : " + userRepository.findAll().get(0));
    }

    @Test
    void userHistoryTest() {
        User user = new User();
        user.setEmail("csw@naver.com");
        user.setName("csw");

        User user2 = new User();
        user2.setEmail("cs@naver.com");
        user2.setName("cs");

        User user3 = new User();
        user3.setEmail("sw@naver.com");
        user3.setName("sw");

        userRepository.save(user);

        user.setName("choi");

        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);
    }

    @Test
    void userRealationTest() {
        User user = new User();
        user.setName("david");
        user.setEmail("david@naver.com");
        user.setGender(Gender.MALE);

        userRepository.save(user);

        user.setName("daniel");
        userRepository.save(user);

        user.setEmail("daniel@naver.com");
        userRepository.save(user);

        // 특정 user 의 history 가 쌓인 것을 확인할 수 있다.
        userHistoryRepository.findAll().forEach(System.out::println);

//        List<UserHistory> result = userHistoryRepository.findByUserId(
//                userRepository.findByEmail("daniel@naver.com").getId());


        List<UserHistory> result = userRepository.findByEmail("daniel@naver.com").getUserHistoryList();

        result.forEach(System.out::println);

        // userHistory 배열의 첫번째 유저의 값을 가져온다.
        System.out.println("UserHistory.getUser() : "+userHistoryRepository.findAll().get(0).getUser());
    }

    private Sort getSort() {
        return Sort.by(
                Sort.Order.desc("id"),
                Sort.Order.asc("name"),
                Sort.Order.desc("createdAt"),
                Sort.Order.asc("updatedAt")
        );
    }


}