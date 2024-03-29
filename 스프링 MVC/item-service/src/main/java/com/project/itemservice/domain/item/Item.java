package com.project.itemservice.domain.item;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {

    private long id;
    private String name;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String name, Integer price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
