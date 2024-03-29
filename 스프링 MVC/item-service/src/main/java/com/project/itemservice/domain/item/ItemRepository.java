package com.project.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }


    public Item update(long id, Item updateParam) {
        Item item = findById(id);
        item.setName(updateParam.getName());
        item.setPrice(updateParam.getPrice());
        item.setQuantity(updateParam.getQuantity());

        return item;
    }

    public void clearStore() {
        store.clear();
    }

}
