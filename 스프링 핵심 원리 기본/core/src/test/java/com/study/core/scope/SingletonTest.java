package com.study.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {

    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        System.out.println("find sb1");
        SingletonBean sb1 = ac.getBean(SingletonBean.class);
        System.out.println("find sb2");
        SingletonBean sb2 = ac.getBean(SingletonBean.class);

        Assertions.assertThat(sb1).isSameAs(sb2);
        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean {

        @PostConstruct
        public void init() {
            System.out.println("init!");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("destroyed!");
        }
    }
}
