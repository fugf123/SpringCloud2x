package com.imooc.product.client.client;

import com.imooc.product.client.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**HystrixFeign
 * 使用H
 */
@FeignClient(name = "product",configuration = FeignConfiguration.class,
        fallback = ProductClient.ProductClientFallBack.class)  //调用服务
@Component
public interface ProductClient {
    @GetMapping("/msg") //调用方法
    String productMsg();
    @Component
    class ProductClientFallBack implements ProductClient{
        /**
         * 服务降级回调函数
         * @return
         */
        @Override
        public String productMsg() {
            System.out.println("productMsg服务降级");
            return "productMsg服务降级";
        }
    }
}
