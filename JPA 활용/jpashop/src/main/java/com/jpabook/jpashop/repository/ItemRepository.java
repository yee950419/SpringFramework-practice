package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.item.Item;
import com.jpabook.jpashop.dto.BookDto;
import com.jpabook.jpashop.exception.DuplicateIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if(item.getId() == null) {
            em.persist(item);
        }
        else {
            throw new DuplicateIdException("중복된 회원 오류");
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
