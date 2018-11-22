package com.imooc.order.client.client;

import com.imooc.order.client.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "product",configuration = FeignConfiguration.class)  //调用服务
@Component
public interface ProductClient {
    @GetMapping("/msg1") //调用方法
    String productMsg();
}
