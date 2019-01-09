package com.imooc.order.web.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

	// 声明队列
	@Bean
	public Queue queue1() {
		return new Queue("hello.queue1", true); // true表示持久化该队列
	}

	@Bean
	public Queue queue2() {
		return new Queue("hello.queue2", true);
	}

	@Bean
	public Queue queue3() {
		return new Queue("hello.queue3", true);
	}


	/**
	 * 听这个名称就知道这个模式和路由有关，我们上一篇说的广播的模式这种方式有点缺少灵活性，
	 * 他只是广播给所有人，假如我们有个场景是这样的：我们总共有10个消费者，
	 * 而生产者前几条消息发送给前5个，后几条消息发送给之后的人，这时候如果使用广播模式发现无法达到我们的效果。
	 * 这时候就发现广播这种模式不灵活，没法根据我们的想法传送消息。我们用direct路由器来替换它。
	 * direct路由器背后的路由算法很简单：只有当消息的路由键routing key与队列的绑定键binding key完全匹配时，该消息才会进入该队列
	 */

	// 声明topic的交换机
	@Bean
    TopicExchange topicExchange() {
		return new TopicExchange("topicExchange");
	}

	// 绑定 queue1 的key.1
	@Bean
	public Binding binding1() {
		return BindingBuilder.bind(queue1()).to(topicExchange()).with("key.1");
	}

	//*（星号）可以代替一个任意标识符 ；#（井号）可以代替零个或多个标识符 key可以玩花的比如：key.1* 1开头的两位数 key.1# 1开头的任意
	@Bean
	public Binding binding2() {
		return BindingBuilder.bind(queue2()).to(topicExchange()).with("key.#");
	}

}
