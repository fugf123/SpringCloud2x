package com.imooc.user.SpringJms;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:consumer.xml");
    }
}
