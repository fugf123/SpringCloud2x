package com.imooc.product.web.enums;


public enum ResultEnum {

    PRODUCT_NO_EXIST(0,"商品不存在")
    ;

    Integer code;
    String msg;
    ResultEnum(Integer code,String msg){
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
