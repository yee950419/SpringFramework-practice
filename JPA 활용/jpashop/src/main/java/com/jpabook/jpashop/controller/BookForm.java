package com.jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class BookForm {

    private Long id;

    @NotEmpty(message = "책 이름은 필수사항입니다")
    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;
}
