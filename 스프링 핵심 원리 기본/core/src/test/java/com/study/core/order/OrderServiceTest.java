package com.study.core.order;

import com.study.core.member.Grade;
import com.study.core.member.Member;
import com.study.core.member.MemberService;
import com.study.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void 주문() {
        Member member1 = new Member(1L, "sanghak", Grade.BASIC);
        Member member2 = new Member(2L, "joohak", Grade.VIP);
        memberService.join(member1);
        memberService.join(member2);

        Order order1 = orderService.createOrder(member1.getId(), "computer", 10000);
        Order order2 = orderService.createOrder(member2.getId(), "computer", 10000);

        Assertions.assertThat(order1.calculatePrice()).isEqualTo(10000);
        Assertions.assertThat(order2.calculatePrice()).isEqualTo(9000);
        System.out.println("order1 = " + order1);
        System.out.println("order2 = " + order2);
    }
}