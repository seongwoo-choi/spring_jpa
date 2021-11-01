package com.example.bookmanager.domain.converter;


import com.example.bookmanager.repository.dto.BookStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

// <X, Y> => <엔티티의 어트리뷰트, 데이터베이스 컬럼 타입>
// Integer => wrapperd 타입을 사용한 이유 => 제너릭에서는 wrapperd 타입을 사용해야 한다.
@Converter(autoApply = true)
public class BookStatusConverter implements AttributeConverter<BookStatus, Integer> {


    // BookStatus 라는 객체를 받아서 데이터베이스에 저장을 어떻게 할 것이냐
    @Override
    public Integer convertToDatabaseColumn(BookStatus attribute) {
        // DB 에는 integer code 가 저장
        return attribute.getCode();
    }

    // DB 에서 Integer 값을 받아서 BookStatus 를 만들어준다.
    @Override
    public BookStatus convertToEntityAttribute(Integer dbData) {
        return dbData != null ? new BookStatus(dbData) : null;
    }
}
