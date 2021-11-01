package com.example.bookmanager.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable // 임베드를 할 수 있는 클래스라는 뜻
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String city; // 시

    private String district; // 구

    @Column(name = "address_detail")
    private String detail; // 상세주소

    private String zipCode; // 우편번호

}
