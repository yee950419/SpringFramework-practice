package com.study.core.discount;

import com.study.core.annotation.SubDiscountPolicy;
import com.study.core.member.Grade;
import com.study.core.member.Member;
import org.springframework.stereotype.Component;

@Component
@SubDiscountPolicy
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else{
            return 0;
        }
    }
}
