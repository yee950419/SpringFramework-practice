package com.study.core.member;

import com.study.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        Member member = new Member(1L, "member1", Grade.BASIC);
        memberService.join(member);
        Member result = memberService.findMember(member.getId());
        Assertions.assertThat(result).isEqualTo(member);
    }
}
