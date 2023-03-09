package com.study.core.order;

import com.study.core.annotation.SubDiscountPolicy;
import com.study.core.discount.DiscountPolicy;
import com.study.core.member.Member;
import com.study.core.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private final MemberService memberService;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberService memberService, @SubDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberService = memberService;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberService.findMember(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
