package com.imooc.order;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.imooc.product.client.client")  //扫描product服务
@EnableCircuitBreaker
@ComponentScan(basePackages = "com.imooc")
@EnableHystrixDashboard
//@SpringCloudApplication
public class OrderApplication {

	public static void main(String[] args) throws IOException {
//		Properties properties = new Properties();
//		InputStream inputStream = Application.class.getClassLoader().getResourceAsStream("druid.properties");
//		properties.load(inputStream);
//		SpringApplication app = new SpringApplication(Application.class);
//		app.setDefaultProperties(properties);
//		app.run(args);
		SpringApplication.run(OrderApplication.class, args);
	}
}
