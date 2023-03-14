package com.study.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member();
        member.setUsername("sanghak");
        member.setAge(5);

        memberRepository.save(member);
        Member result = memberRepository.findById(member.getId());

        Assertions.assertThat(result).isEqualTo(member);
    }

    @Test
    void findAll() {
        Member member1 = new Member("member1", 10);
        Member member2 = new Member("member2", 15);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> memberList = memberRepository.findAll();

        for (Member member : memberList) {
            System.out.println(member.getUsername());
        }

        Assertions.assertThat(memberList.size()).isEqualTo(2);
        Assertions.assertThat(memberList).contains(member1, member2);
    }
}