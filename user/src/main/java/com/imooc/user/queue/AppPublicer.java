package com.imooc.user.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/***
 * 消息发布者
 */
public class AppPublicer {
    private static final String url = "tcp://localhost:61616";
    private static final String topicName = "topic-test";
    public static void main(String[] args) throws JMSException {
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
        //6.创建生产者
        MessageProducer producer = session.createProducer(destination);
        for (int i = 0; i < 100; i++) {
            //7.创建消息
            TextMessage textMessage = session.createTextMessage("今天你吃了吗?"+i);
            //8.发布消息
            producer.send(textMessage);
            System.out.println("发送消息:"+textMessage);
        }
        connection.close();
    }
}