package com.jpabook.jpashop.domain.item;

import com.jpabook.jpashop.exception.NotEnoughStockException;
import org.junit.jupiter.api.Assertions;
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
        Assertions.assertThrows(NotEnoughStockException.class, () -> {
            album.removeStock(7);
        });
    }
}