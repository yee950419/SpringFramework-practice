package com.project.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        Item item = new Item();
        item.setItemName("item1");
        item.setPrice(30000);
        item.setQuantity(5);

        Item savedItem = itemRepository.save(item);

        Item result = itemRepository.findById(item.getId());
        Assertions.assertThat(result.getId()).isEqualTo(savedItem.getId());
    }

    @Test
    void findAll() {
        Item item1 = new Item("item1", 20000, 5);
        itemRepository.save(item1);

        Item item2 = new Item("item2", 30000, 10);
        itemRepository.save(item2);

        List<Item> itemList = itemRepository.findAll();
        Assertions.assertThat(itemList.size()).isEqualTo(2);

    }

    @Test
    void update() {
        Item item = new Item("item", 50000, 4);
        Item savedItem = itemRepository.save(item);
        long itemId = savedItem.getId();
        
        Item newItem = new Item("updateItem", 100000, 5);
        Item updatedItem = itemRepository.update(item.getId(), newItem);

        Item findItem = itemRepository.findById(itemId);

        Assertions.assertThat(findItem.getItemName()).isEqualTo(updatedItem.getItemName());
        Assertions.assertThat(findItem.getPrice()).isEqualTo(updatedItem.getPrice());
        Assertions.assertThat(findItem.getQuantity()).isEqualTo(updatedItem.getQuantity());
    }
}