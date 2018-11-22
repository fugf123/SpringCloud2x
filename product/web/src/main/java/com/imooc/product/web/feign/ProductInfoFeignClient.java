package com.imooc.product.web.feign;


import com.imooc.product.web.dataobject.ProductInfo;

public interface ProductInfoFeignClient {

    ProductInfo findByID(int id);
}
