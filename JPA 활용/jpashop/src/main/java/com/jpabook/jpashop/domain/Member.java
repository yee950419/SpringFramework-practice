package com.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty   //엔티티에서 Validation을 설정하기보단, DTO 단에서 설정하는 것이 좋음(엔티티는 수많은 코드에 영향을 끼치므로)
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") //"member"라는 필드명에 의해 mapped 되었다
    private List<Order> orders = new ArrayList<>();
}
