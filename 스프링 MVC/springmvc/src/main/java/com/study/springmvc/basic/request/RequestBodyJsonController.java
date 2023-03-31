package com.study.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void RequestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write(("ok"));
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String RequestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        log.info("messageBody = {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String RequestBodyJsonV3(HttpEntity<HelloData> httpEntity){

        HelloData helloData = httpEntity.getBody();
        log.info("messageBody = {}", helloData);
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    //@RequestBody 어노테이션 생략시 @ModelAttribute로 적용됨으로 생략하면 안됨
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String RequestBodyJsonV4(@RequestBody HelloData helloData){

        log.info("messageBody = {}", helloData);
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData RequestBodyJsonV5(@RequestBody HelloData helloData){

        log.info("messageBody = {}", helloData);
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());

        return helloData;
    }
}
