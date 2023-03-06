package com.study.core.order;

import com.study.core.discount.DiscountPolicy;
import com.study.core.discount.FixDiscountPolicy;
import com.study.core.member.Member;
import com.study.core.member.MemberService;
import com.study.core.member.MemberServiceImpl;

public class OrderServiceImpl implements OrderService{

    private final MemberService memberService = new MemberServiceImpl();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberService.findMember(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
