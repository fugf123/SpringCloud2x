package com.imooc.product.web.feign;


import com.imooc.product.web.dataobject.ProductInfo;

public class FeignClientFallback  implements ProductInfoFeignClient {
    @Override
    public ProductInfo findByID(int id) {
        ProductInfo user = new ProductInfo();
        user.setProductId("1");
        user.setProductDescription("请求失败！");
        return user;
    }
}