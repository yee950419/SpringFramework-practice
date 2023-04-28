package com.jpabook.jpashop.domain.item;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemTest {

    @Test
    public void item() {
        Item album = new Album();
        album.setStockQuantity(5);
        album.removeStock(5);
        System.out.println(album.getStockQuantity());
    }
}