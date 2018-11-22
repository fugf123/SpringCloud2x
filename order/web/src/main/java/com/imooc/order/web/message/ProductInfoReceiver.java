//package com.imooc.order.web.message;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//
//import javax.transaction.Transactional;
//
//@Component
//@Slf4j
//@Transactional
//public class ProductInfoReceiver {
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
//    public void process(String message) {
//        System.out.println(message);
//        //存储到redis
//
//        stringRedisTemplate.opsForValue().set("1",messge);
//
//    }
//}
