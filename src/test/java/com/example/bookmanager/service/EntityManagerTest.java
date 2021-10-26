package com.example.bookmanager.service;

import com.example.bookmanager.domain.User;
import com.example.bookmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional // 쓰기 지연 현상 => DB 에 반영되지 않고 영속성 컨텍스트에 저장, 테스트 케이스이기 때문에 롤백 트랜잭션이 일어난다.
public class EntityManagerTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void EntityManagerTest() {
        // User 엔티티에서 u 라고 표현을 했을 때 그 값을 가져와라
        System.out.println(entityManager.createQuery("select u from User u").getResultList());
        // = userRepository.findAll() 과 같다.
    }

    // 조회 시 영속성 컨텍스트의 cache 를 통해 빠르게 데이터를 불러왔다.
    @Test
    void cacheFindTest() {
        System.out.println(userRepository.findByEmail("a@naver.com"));
        System.out.println(userRepository.findByEmail("a@naver.com"));
        System.out.println(userRepository.findByEmail("a@naver.com"));
        System.out.println(userRepository.findById(2L).get());
        System.out.println(userRepository.findById(2L).get());
        System.out.println(userRepository.findById(2L).get());

        userRepository.deleteById(1L);
    }

    @Test
    void cacheFindTest2() {
        User user = userRepository.findById(1L).get();
        user.setName("kados");

        userRepository.save(user);

        // 실제 디비에 반영하는 부분
        // userRepository.flush();

        System.out.println("--------------------------------");

        user.setEmail("kados@naver.com");
        userRepository.save(user);

        // select * from user => 위의 최신 데이터를 가져오기 위해서 영속성 컨텍스트의 값을 사용, 나머지는 DB 값을 가져온다.
        // data 를 머지하는 작업이 필요로 하다. 영속성 컨텍스트에 있는 값을 flush(DB 로 모든 데이터를 보낸다) 하고 그 후에 select * from user 를 하여 영속성 컨텍스트에 가져온다.
        System.out.println(userRepository.findAll());
    }
}
