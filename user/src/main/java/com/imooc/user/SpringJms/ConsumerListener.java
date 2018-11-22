package com.imooc.user.SpringJms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 配置消息监听器
 */
public class ConsumerListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println(textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
