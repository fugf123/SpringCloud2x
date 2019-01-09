package com.imooc.order.web.message;


import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class MqReceiver {
    //1.
    //@RabbitListener(queues = "myQueue")
    //2. 自动创建队列
    //@RabbitListener(queuesToDeclare = @Queue("myQueue"))
    //3. 自动创建，Exchange和Queue绑定
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))

    public void process(String message) {
        System.out.println(message);
        //log.info("mq:{}",message);
    }

    /**
     *exchange 队列消息分组
     * @param messge
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("computerQueue"),
            exchange = @Exchange("myOrder"),
            key = "computer"
    ))
    public void processComputer(String messge) {
        System.out.println(messge);
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("fruitQueue"),
            exchange = @Exchange("myOrder"),
            key = "fruit"
    ))
    public void processFruitComputer(String messge) {
        System.out.println(messge);
    }

    /**
     * rabbitmq 原生api使用 手工签收
     * @param message
     * @param headers
     * @param channel
     */
    @RabbitHandler//标识接收消息
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order-queue",durable = "true"),//是否持久化
            exchange = @Exchange(name = "order-exchange",durable = "true",type = "topic"),
            key = "order.*"

    ))
    public void onOrderMessage(@Payload String message, @Headers Map<String,Object> headers,
                               Channel channel) throws IOException {
        Long delivery_tag =(Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //手工Ack签收
        channel.basicAck(delivery_tag,false);//确认签收 第一个参数  第二个参数表示是否批量接收

    }
}
