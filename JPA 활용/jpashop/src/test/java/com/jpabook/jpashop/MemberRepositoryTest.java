package com.jpabook.jpashop;

import com.jpabook.jpashop.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testMember() {
        Member member = new Member();
        member.setName("userB");

        Long savedId = memberRepository.save(member);
        Member newMember = memberRepository.find(savedId);

        Assertions.assertThat(member.getId()).isEqualTo(newMember.getId());
        Assertions.assertThat(member.getName()).isEqualTo(newMember.getName());
        Assertions.assertThat(member).isEqualTo(newMember);
    }

}