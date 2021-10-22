package com.example.bookmanager.domain.listener;

import java.time.LocalDateTime;


// createdAt 과 updatedAt 을 감시하기 위한 Auditable 인터페이스
public interface Auditable {
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();

    void setCreatedAt(LocalDateTime createdAt);
    void setUpdatedAt(LocalDateTime updatedAt);
}
