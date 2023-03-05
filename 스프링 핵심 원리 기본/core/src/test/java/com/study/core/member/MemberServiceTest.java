package com.study.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        Member member = new Member(1L, "member1", Grade.BASIC);
        memberService.join(member);
        Member result = memberService.findMember(member.getId());
        Assertions.assertThat(result).isEqualTo(member);
    }
}
