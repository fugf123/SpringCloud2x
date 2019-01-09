package com.imooc.order.web.message;

import com.imooc.order.web.OrderApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 发送mq消息测试
 */
@Slf4j
@Component
public class MqReceiverTest extends OrderApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void send() {
        amqpTemplate.convertAndSend("myQueue","date:"+System.currentTimeMillis());
    }
    @Test
    public void sendComputer() {//Exchange ,routingKey. messge
        amqpTemplate.convertAndSend("myOrder","computer","2222S");
    }
    @Test
    public void sendFruit() {
        amqpTemplate.convertAndSend("myOrder","fruit","date:"+System.currentTimeMillis());
    }
    @Test
    public void sendFruitR() {
        rabbitTemplate.convertAndSend("order-exchange","order.*","date:"+System.currentTimeMillis());
    }
}
