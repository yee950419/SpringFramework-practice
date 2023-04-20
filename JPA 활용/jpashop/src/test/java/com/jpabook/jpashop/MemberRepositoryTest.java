package com.jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testMember() {
        Member member = new Member();
        member.setUsername("userB");

        Long savedId = memberRepository.save(member);
        Member newMember = memberRepository.find(savedId);

        Assertions.assertThat(member.getId()).isEqualTo(newMember.getId());
        Assertions.assertThat(member.getUsername()).isEqualTo(newMember.getUsername());
        Assertions.assertThat(member).isEqualTo(newMember);
    }

}