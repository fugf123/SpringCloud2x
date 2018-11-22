package com.imooc.user.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息消费者
 */
public class AppConsumer {
    private static final String url = "tcp://localhost:61616";
    private static final String queueName = "queue-test";
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
        Destination destination = session.createQueue(queueName);
        //Queue queue = session.createQueue(queueName);
        //6.创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        //7.创建监听器
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage)message;
                try {
                    System.out.println("接收到的消息:"+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //异步操作所以需要在异步操作处理完之后再关闭连接
        //connection.close();
    }
}