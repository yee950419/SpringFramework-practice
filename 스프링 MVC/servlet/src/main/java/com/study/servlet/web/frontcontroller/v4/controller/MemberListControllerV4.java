package com.study.servlet.web.frontcontroller.v4.controller;

import com.study.servlet.domain.member.Member;
import com.study.servlet.domain.member.MemberRepository;
import com.study.servlet.web.frontcontroller.ModelView;
import com.study.servlet.web.frontcontroller.v3.ControllerV3;
import com.study.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members = memberRepository.findAll();

        model.put("memberList", members);
        return "member-list";
    }
}
