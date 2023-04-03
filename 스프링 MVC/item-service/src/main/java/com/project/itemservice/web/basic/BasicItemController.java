package com.project.itemservice.web.basic;

import com.project.itemservice.domain.item.Item;
import com.project.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> itemList = itemRepository.findAll();
        model.addAttribute("items", itemList);

        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String showItemById(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String save(@RequestParam String itemName,
                       @RequestParam Integer price,
                       @RequestParam Integer quantity,
                       Model model) {
        Item item = new Item(itemName, price, quantity);
        itemRepository.save(item);

        model.addAttribute(item);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String saveV2(@ModelAttribute Item item, Model model) {

        itemRepository.save(item);

        model.addAttribute("item", item);
        return "basic/item";
    }

    // Model에 "item" 이름으로 item 객체를 추가해줌
//    @PostMapping("/add")
    public String saveV3(@ModelAttribute("item") Item item) {

        itemRepository.save(item);

        return "basic/item";
    }

    // Model에 "item" 이름으로 item 객체를 추가해줌
    //Item -> item으로 맨 앞글자만 소문자로 바꿔서 넣어줌
//    @PostMapping("/add")
    public String saveV4(@ModelAttribute Item item) {

        itemRepository.save(item);

        return "basic/item";
    }

    //ModelAttribute 마저 생략 가능
//    @PostMapping("/add")
    public String saveV5(Item item) {

        itemRepository.save(item);

        return "basic/item";
    }

    //PRG(Post -> Redirect -> Get) 방식 (새로고침 시 중복 등록되는 문제 방지)
//    @PostMapping("/add")
    public String saveV6(Item item) {

        itemRepository.save(item);

        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String saveV7(Item item, RedirectAttributes redirectAttributes) {

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    @PostConstruct
    public void init() {

        Item item1 = new Item("item1", 20000, 3);
        Item item2 = new Item("item2", 30000, 10);
        itemRepository.save(item1);
        itemRepository.save(item2);
    }
}
