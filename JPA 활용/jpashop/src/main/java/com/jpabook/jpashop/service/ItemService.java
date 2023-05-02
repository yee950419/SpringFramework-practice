package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.item.Book;
import com.jpabook.jpashop.domain.item.Item;
import com.jpabook.jpashop.dto.BookDto;
import com.jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(BookDto bookDto) {
        Book item = new Book();
        item.createBook(bookDto.getName(), bookDto.getPrice(),
                bookDto.getStockQuantity(), bookDto.getAuthor(), bookDto.getIsbn());

        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(BookDto bookDto) {
        Book item = (Book) itemRepository.findOne(bookDto.getId());
        item.createBook(bookDto.getName(), bookDto.getPrice(),
                bookDto.getStockQuantity(), bookDto.getAuthor(), bookDto.getIsbn());
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
