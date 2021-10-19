package com.example.bookmanager.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private String name;
    private String email;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
