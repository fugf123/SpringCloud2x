package com.imooc.apigatway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Component;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableZuulProxy
public class ApiGatwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatwayApplication.class, args);
    }

    /***
     *功能:动态读取配置路由
     * 和ZuulProperties 类型
     * 可以使用启动类替代
     */
    @Component
    public class ZuulConfig {
        @ConfigurationProperties("zuul")
        @RefreshScope
        public ZuulProperties zuulProperties() {
            return new ZuulProperties();
        }
    }
}
