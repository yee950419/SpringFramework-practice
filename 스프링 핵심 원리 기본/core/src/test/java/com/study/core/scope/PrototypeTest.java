package com.study.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입, 초기화까지만 관여 (종료 메서드 호출 X)
 * 따라서 프로토타입 빈은 프로토타입 빈을 조회한 클라이언트가 관리해야 한다. 종료 메서드에 대한 호출도 클라이언트가 직접 해야한다.
 */
public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find pb1");
        PrototypeBean pb1 = ac.getBean(PrototypeBean.class);
        System.out.println("find pb2");
        PrototypeBean pb2 = ac.getBean(PrototypeBean.class);

        Assertions.assertThat(pb1).isNotSameAs(pb2);
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("init1");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("destroy!");
        }
    }

}
