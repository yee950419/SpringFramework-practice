package com.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;

/**
 * 값 타입을 넣는 공간이므로 setter를 제거하고 변경 불가능하게 설계해야 한다.
 * 이유는 JPA 구현 라이브러리가 객체를 생성할 때 리플랙션 같은 기술을 사용할 수 있도록 지원해야 하기 때문이다.
 */
@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
