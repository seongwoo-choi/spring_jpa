package com.example.bookmanager.domain.listener;

import com.example.bookmanager.domain.User;
import com.example.bookmanager.domain.UserHistory;
import com.example.bookmanager.repository.UserHistoryRepository;
import com.example.bookmanager.support.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

// 자동화 된 로직을 구현하는데 아주 효과적이다.
public class UserEntityListener {
    @PostPersist
    @PostUpdate
    public void prePersistAndPreUpdate(Object o) {
        // UserEntityListener 가 Spring Bean 에 등록되지 않고, UserHistoryRepository 를 DI 받으면 값이 아예 빈 값이기 때문에
        // BeanUtils 의 getBean 을 사용해서 UserHistoryRepository 를 우회해서 가져온다.
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user = (User) o;

        UserHistory userHistory = new UserHistory();
        userHistory.setUserId(user.getId());
        userHistory.setEmail(user.getEmail());
        userHistory.setName(user.getName());
        userHistory.setGender(user.getGender());

        userHistoryRepository.save(userHistory);
    }
}
