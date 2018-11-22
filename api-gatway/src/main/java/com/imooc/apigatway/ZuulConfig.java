package com.imooc.apigatway;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Component;

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
