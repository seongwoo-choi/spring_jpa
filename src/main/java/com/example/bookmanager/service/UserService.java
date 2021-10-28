package com.example.bookmanager.service;

import com.example.bookmanager.domain.User;
import com.example.bookmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class UserService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void put() {
        // 비영속 상태이다.
        // 단순히 자바 객체로만 존재 => 이 메서드 호출이 종료되면 값이 사라지는 데이터이다.
        User user = new User();
        user.setName("sw");
        user.setEmail("sw@naver.com");

        // 영속화 효과가 있다. 다만 바로 반영이 되지 않는다.
        // userRepository.save(user);

        // 영속성 컨텍스트가 해당 객체를 관리한다.
        // 엔티티 매니저를 이용하여 직접 영속화를 시킨다.
        entityManager.persist(user);

        // 준영속 상태로 만든다. => 영속화 된 객체를 영속성 컨텍스트에서 꺼낸다.
        // .clear, .close 도 모두 준영속 상태로 만든다. 그러나 detach 보단 더 파괴적인 방법이다.
//        entityManager.detach(user);


        // DB 에 저장되지 못하고 가비지 컬렉터에 의해 사라지지 않고 엔티티 매니저에 의해 DB 에 값이 저장된다.
        // 영속성 컨텍스트에 의해 객체가 관리되면 setter 를 통해 값을 변경해도 db 에 값이 저장된다.
        // 준영속 상태에 있는 객체일 경우 DB 에 저장되지 않는다.
        user.setName("nameUserAfterPersist");

        // 준영속 상태였던 객체를 다시 영속화 해준다.
        entityManager.merge(user);
        // clear 를 사용하기 전에 꼭 flush 를 해주기를 권장한다.
//        entityManager.flush();
//        entityManager.clear();

        // 삭제 상태
        entityManager.refresh(user);
    }



}
