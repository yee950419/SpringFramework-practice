package com.jpabook.jpashop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {

    private Long memberId;
    private Long itemId;
    private int count;
}
