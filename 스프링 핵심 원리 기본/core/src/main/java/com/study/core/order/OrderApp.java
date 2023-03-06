package com.study.core.order;

import com.study.core.member.Grade;
import com.study.core.member.Member;
import com.study.core.member.MemberService;
import com.study.core.member.MemberServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Member member = new Member(1L, "sanghak", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(member.getId(), "computer", 10000);

        System.out.println(order);
        System.out.println(order.calculatePrice());
    }
}
