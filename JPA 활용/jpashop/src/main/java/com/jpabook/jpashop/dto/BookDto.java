package com.jpabook.jpashop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookDto {

    private Long id;
    private String name;
    private String author;
    private int price;
    private int stockQuantity;
    private String isbn;
}
