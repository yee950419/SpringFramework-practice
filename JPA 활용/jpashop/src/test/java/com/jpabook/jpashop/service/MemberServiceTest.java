package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;
    
    @Test
    public void join() {
        //given
        Member member = new Member();
        member.setName("상학");
        member.setAddress(new Address("용인시 수지구", "성복2로 126", "16815"));

        //when
        Long joinedId = memberService.join(member);
        Member findMember = memberService.findOne(joinedId);

        //then
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    
    @Test()
    public void duplicateException() {
        //given
        Member member1 = new Member();
        member1.setName("상학");

        Member member2 = new Member();
        member2.setName("상학");

        //when
        memberService.join(member1);
        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });

    }
}