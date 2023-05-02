package com.jpabook.jpashop.controller;

import com.jpabook.jpashop.domain.item.Book;
import com.jpabook.jpashop.domain.item.Item;
import com.jpabook.jpashop.dto.BookDto;
import com.jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/new")
    public String create(@Valid BookForm bookForm) {

        BookDto bookDto = new BookDto();
        bookDto.setName(bookForm.getName());
        bookDto.setAuthor(bookForm.getAuthor());
        bookDto.setPrice(bookForm.getPrice());
        bookDto.setStockQuantity(bookForm.getStockQuantity());
        bookDto.setIsbn(bookForm.getIsbn());

        itemService.saveItem(bookDto);
        return "redirect:/";
    }

    @GetMapping
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Book item = (Book) itemService.findOne(itemId);

        BookForm bookForm = new BookForm();
        bookForm.setId(item.getId());
        bookForm.setPrice(item.getPrice());
        bookForm.setName(item.getName());
        bookForm.setAuthor(item.getAuthor());
        bookForm.setStockQuantity(item.getStockQuantity());
        bookForm.setIsbn(item.getIsbn());

        model.addAttribute("form", bookForm);
        return "items/updateItemForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable String itemId, @ModelAttribute("form") BookForm bookForm) {
        BookDto bookDto = new BookDto();

        bookDto.setId(bookForm.getId());
        bookDto.setName(bookForm.getName());
        bookDto.setPrice(bookForm.getPrice());
        bookDto.setStockQuantity(bookForm.getStockQuantity());
        bookDto.setAuthor(bookForm.getAuthor());
        bookDto.setIsbn(bookForm.getIsbn());

        itemService.updateItem(bookDto);
        return "redirect:/items";
    }
}
