package com.study.core;

import lombok.*;

@Data
public class HelloLombok {

    private final String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok("sanghak");
        helloLombok.setAge(2);
    }
}
