package com.imooc.product.web.controller;

import com.imooc.product.common.ProductInfoOutput;
import com.imooc.product.vo.ResultVo;
import com.imooc.product.web.dataobject.ProductInfo;
import com.imooc.product.web.service.IProductService;
import com.imooc.product.web.vo.ProductInfoVO;
import io.swagger.annotations.*;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/8/9.
 */
@RestController
@RequestMapping("/product")
@Api(value = "产品接口")
public class ProductController {

    @Autowired
    private IProductService _productService;
    @Autowired
    private AmqpTemplate amqpTemplate;
    /*Swagger使用的注解及其说明：

    @Api：用在类上，说明该类的作用。

    @ApiOperation：注解来给API增加方法说明。

    @ApiImplicitParams : 用在方法上包含一组参数说明。

    @ApiImplicitParam：用来注解来给方法入参增加说明。

    @ApiResponses：用于表示一组响应

    @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息

    l   code：数字，例如400

    l   message：信息，例如"请求参数没填好"

    l   response：抛出异常的类

    @ApiModel：描述一个Model的信息（一般用在请求参数无法使用@ApiImplicitParam注解进行描述的时候）

    l   @ApiModelProperty：描述一个model的属性*/



    @GetMapping(value = "/list")
    @ApiOperation(value = "为方法增加方法说明",notes = "swagger测试，返回结果list")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "userNumber", value = "用户编号", required = true, dataType = "Integer")
,    @ApiImplicitParam(paramType="query", name = "userNumber", value = "用户编号", required = true, dataType = "Integer")
    })//用在方法上包含一组参数
    @ApiImplicitParam(paramType="query", name = "userNumber", value = "用户编号", required = true, dataType = "Integer")
    public ResultVo<ProductInfo> findUpAll(@RequestParam(required = false) int id){
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
