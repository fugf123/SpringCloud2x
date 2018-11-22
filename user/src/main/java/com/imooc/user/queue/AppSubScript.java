package com.imooc.user.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息订阅者
 */
public class AppSubScript {
    private static final String url = "tcp://localhost:61616";
    private static final String topicName = "topic-test";
    public static void main(String[] args) throws JMSException {
        //必须先预定主题才能收到消息 即首先应该先启动订阅者在启动消息发布者
        //1.创建连接工厂 ConnectionFactory
        ConnectionFactory factory = new ActiveMQConnectionFactory(url);
        //2.创建连接Connection
        Connection connection = factory.createConnection();
        //3.启动连接
        connection.start();
        //4.创建回话Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建目标
        Destination destination = session.createTopic(topicName);
        //Queue queue = session.createQueue(queueName);
        //6.创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        //7.创建监听器
        consumer.setMessageListener(message -> {
            TextMessage textMessage = (TextMessage)message;
            try {
                System.out.println("接收到的消息:"+textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
        //异步操作所以需要在异步操作处理完之后再关闭连接
        //connection.close();
    }
}