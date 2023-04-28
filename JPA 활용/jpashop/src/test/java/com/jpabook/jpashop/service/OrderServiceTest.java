package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderStatus;
import com.jpabook.jpashop.domain.item.Book;
import com.jpabook.jpashop.domain.item.Item;
import com.jpabook.jpashop.exception.NotEnoughStockException;
import com.jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void orderItem() throws Exception {
        //given
        Member member = createMember();

        int initBookCount = 10;
        Item book = createBook(initBookCount, "JPA 책");

        int orderCount = 5;
        //when
        Long orderedId = orderService.save(member.getId(), book.getId(), orderCount);

        Order findOrder = orderRepository.findOne(orderedId);

        //then
        assertThat(orderedId).isEqualTo(findOrder.getId()); //주문한 order가 잘 save 되었는지 확인
        assertThat(findOrder.getOrderStatus()).isEqualTo(OrderStatus.ORDER); //주문 상태가 ORDER가 맞는지 확인
        assertThat(findOrder.getOrderItems().size()).isEqualTo(1); //주문 상품 수량이 맞는지 확인
        assertThat(findOrder.getTotalPrice()).isEqualTo(book.getPrice() * orderCount); //주문 총 금액이 맞는지 확인
        assertThat(book.getStockQuantity()).isEqualTo(initBookCount - orderCount); //주문한만큼 재고량이 줄었는지 확인
    }

    @Test
    public void cancleOrder() throws Exception {
        //given
        Member member = createMember();
        int initBookCount = 10;
        Item book = createBook(initBookCount, "JPA 책");

        int orderCount = 7;
        Long orderedId = orderService.save(member.getId(), book.getId(), orderCount);

        //when
        orderService.cancleOrder(orderedId);

        //then
        Order cancledOrder = orderRepository.findOne(orderedId);

        assertThat(book.getStockQuantity()).isEqualTo(initBookCount); //취소로 인한 재고량 복구 확인
        assertThat(cancledOrder.getOrderStatus()).isEqualTo(OrderStatus.CANCLE); //주문 상태 취소 확인
    }

    @Test
    public void orderCountExceed() throws Exception {
        //given
        Member member = createMember();
        Item book = createBook(10, "JPA 책");

        //when
        Assertions.assertThrows(NotEnoughStockException.class, () -> {
            orderService.save(member.getId(), book.getId(), 12);
        });
    }

    private Item createBook(int initBookCount, String bookName) {
        Item book = new Book();
        book.setName(bookName);
        book.setStockQuantity(initBookCount);
        book.setPrice(30000);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("상학");
        member.setAddress(new Address("용인시", "수지구", "16851"));
        em.persist(member);
        return member;
    }

}