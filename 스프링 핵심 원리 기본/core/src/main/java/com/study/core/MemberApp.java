package com.study.core;

import com.study.core.member.Grade;
import com.study.core.member.Member;
import com.study.core.member.MemberService;
import com.study.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();

        Member member = new Member(1L, "member1", Grade.VIP);
        memberService.join(member);
        System.out.println(memberService.findMember(1L).getName());
    }
}
