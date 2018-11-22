package com.imooc.product.vo;

/**
 * Created by Administrator on 2018/8/9.
 */
public class ResultVo<T> {

    private Integer code;

    private String msg;

    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ResultVo success(Object data){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("成功!");
        resultVo.setData(data);
        return resultVo;
    }

    public static ResultVo error(String msg){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(1);
        resultVo.setMsg(msg);
        return resultVo;
    }
}
