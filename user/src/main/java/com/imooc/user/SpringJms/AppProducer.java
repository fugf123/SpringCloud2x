package com.imooc.user.SpringJms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppProducer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:producer.xml");
        ProducerService producerService = (ProducerService) context.getBean("producerService");
        producerService.sendMessage("今天你吃了吗");
        context.close();
    }
}
