package com.imooc.product.web.controller;

import com.imooc.product.common.ProductInfoOutput;
import com.imooc.product.vo.ResultVo;
import com.imooc.product.web.dataobject.ProductInfo;
import com.imooc.product.web.service.IProductService;
import com.imooc.product.web.vo.ProductInfoVO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/8/9.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService _productService;
    @Autowired
    private AmqpTemplate amqpTemplate;


    @GetMapping(value = "/list")
    public ResultVo<ProductInfo> findUpAll(){
        List<ProductInfo> list = this._productService.findUpAll();
        System.out.println(list.stream().count());

        List<Integer> ids = list.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
        ProductInfoVO productInfoVO = new ProductInfoVO();
        list.stream().map(e->{
            ProductInfoOutput p = new ProductInfoOutput();
            return p;
        }).collect(Collectors.toList());

        //发送消息
      //  amqpTemplate.convertAndSend("productInfo","product");//注意:需要在消息接收端 rabbitListener 接收消息 创建队列
        return ResultVo.success(list);
    }
}
