package com.study.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[전체 파라미터 조회] - start");
        Enumeration<String> parameterNames = request.getParameterNames();
        parameterNames.asIterator()
                .forEachRemaining(parameterName -> System.out.println(parameterName + " : " + request.getParameter(parameterName)));
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회] - start");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println("[단일 파라미터 조회] - end");

        System.out.println("[복수 파라미터 조회] - start");
        String[] parameterValues = request.getParameterValues("username");
        String[] ages = request.getParameterValues("age");
        for (String parameterValue : parameterValues) {
            System.out.println(parameterValue);
        }
        for (String s : ages) {
            System.out.println(s);
        }
        System.out.println("[복수 파라미터 조회] - end");

        response.getWriter().write("OK");

    }
}
