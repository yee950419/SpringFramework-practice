package com.study.servlet.web.frontcontroller.v3.controller;

import com.study.servlet.domain.member.Member;
import com.study.servlet.domain.member.MemberRepository;
import com.study.servlet.web.frontcontroller.ModelView;
import com.study.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();
        ModelView mv = new ModelView("member-list");
        mv.getModel().put("member-list", members);

        return mv;
    }
}
