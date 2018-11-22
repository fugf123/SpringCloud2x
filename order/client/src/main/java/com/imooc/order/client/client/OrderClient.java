package com.imooc.order.client.client;

import com.imooc.order.client.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "order",configuration = FeignConfiguration.class)
@Component
public interface OrderClient {
    @GetMapping("msg")
    String productMsg();
}
