package com.imooc.order.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Receiver {

	@RabbitListener(queues = "hello.queue1")
	public String processMessage1(String msg) {
		log.info(Thread.currentThread().getName() + " 接收到来自hello.queue1队列的消息：" + msg);
		return msg.toUpperCase();
	}

	@RabbitListener(queues = "hello.queue2")
	public void processMessage2(String msg) {
		log.info(Thread.currentThread().getName() + " 接收到来自hello.queue2队列的消息：" + msg);
	}

	@RabbitListener(queues = "hello.queue3")
	public void process(String hello) {
		log.info("Receiver  : " + hello);
	}

}
