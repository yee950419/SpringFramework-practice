package com.study.springmvc.basic.response;

import com.study.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@Controller
//@ResponseBody
@RestController //-> @ResponseBody + @Controller
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyStringV1(HttpServletResponse response) throws IOException {
        response.getWriter().write(("ok"));
    }

    @GetMapping("/response-body-string-v2")
    public HttpEntity<String> responseBodyStringV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

//    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyStringV3() throws IOException {
        return "ok";
    }

    //상황에 따라 responseStatus를 동적으로 바꾸고 싶을땐 ResponseEntity를 사용
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("상학");
        helloData.setAge(25);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    //어노테이션으로 ststus를 확정짓기 때문에 동적으로 바꾸긴 어려움
    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("상학");
        helloData.setAge(25);

        return helloData;
    }
}
