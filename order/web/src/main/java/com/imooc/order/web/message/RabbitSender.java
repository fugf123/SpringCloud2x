package com.imooc.order.web.message;

import com.rabbitmq.client.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //confirm回调函数  rabbitmq 自带的回调函数
//    final ConfirmCallback confirmCallback = new ConfirmCallback() {
//        @Override
//        public void handle(long l, boolean b){
//            System.err.println();
//        }
//    };
    //confirm回调函数  amqp中rabbitmq 自带的回调函数
    final RabbitTemplate.ConfirmCallback callback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            String messageId = correlationData.getId();
            if(ack){
                //进行业务逻辑的操作
            }else{
                //失败则进行具体的后续操作:重试或者补偿等手段
                System.out.println("消息接收失败");
            }
        }
    };
    public void sender(String message){
        rabbitTemplate.setConfirmCallback(callback);//消息传递成功之后调用
        CorrelationData correlationData = new CorrelationData(message);
        rabbitTemplate.convertAndSend("order-exchange","order.abc",message,correlationData);
    }

}
