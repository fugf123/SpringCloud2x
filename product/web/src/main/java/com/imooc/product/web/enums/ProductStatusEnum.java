package com.imooc.product.web.enums;

/**
 * Created by Administrator on 2018/8/9.
 */
public enum ProductStatusEnum {
    UP(0,"在架"),
    DOWN(1,"下架"),
    ;

    Integer code;
    String msg;
    ProductStatusEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}