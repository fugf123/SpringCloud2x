package com.imooc.user.SpringJms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.*;

public class ProducerServiceImpl implements ProducerService {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Resource(name = "queueDestination")
    //@Resource(name = "topicDestination") 主题模式
    private Destination destination;
    @Override
    public void sendMessage(final String message) {
        /**
         * 使用jmsTemplate 发送消息
         */
        jmsTemplate.send(destination, session -> {
            //创建消息
            TextMessage textMessage = session.createTextMessage(message);
            System.out.println("发送消息:"+textMessage.getText());
            return textMessage;
        });

    }
}
