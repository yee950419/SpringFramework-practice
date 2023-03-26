package com.study.servlet.web.springmvc.v1;

import com.study.servlet.domain.member.Member;
import com.study.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SpringMemberListControllerV1 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members")
    public ModelAndView process() {

        List<Member> memberList = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("member-list");
        mv.addObject("memberList", memberList);

        return mv;
    }
}
