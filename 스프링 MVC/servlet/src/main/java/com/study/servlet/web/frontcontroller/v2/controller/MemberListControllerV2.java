package com.study.servlet.web.frontcontroller.v2.controller;

import com.study.servlet.domain.member.Member;
import com.study.servlet.domain.member.MemberRepository;
import com.study.servlet.web.frontcontroller.MyView;
import com.study.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> memberList = memberRepository.findAll();
        request.setAttribute("memberList", memberList);

        return new MyView("/WEB-INF/views/member-list.jsp");
    }
}
