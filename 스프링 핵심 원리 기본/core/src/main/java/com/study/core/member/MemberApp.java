//package com.study.core.member;
//
//import com.study.core.AppConfig;
//import com.study.core.member.Grade;
//import com.study.core.member.Member;
//import com.study.core.member.MemberService;
//import com.study.core.member.MemberServiceImpl;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//public class MemberApp {
//
//    public static void main(String[] args) {
//
////        AppConfig appConfig = new AppConfig();
////        MemberService memberService = appConfig.memberService();
//        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//        MemberService memberService = ac.getBean("memberService", MemberService.class);
//
//        Member member = new Member(1L, "member1", Grade.VIP);
//        memberService.join(member);
//        System.out.println(memberService.findMember(1L).getName());
//    }
//}
