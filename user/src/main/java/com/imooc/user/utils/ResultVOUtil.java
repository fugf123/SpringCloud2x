package com.imooc.user.utils;


import com.imooc.user.vo.ResultVo;

/**
 * Created by 廖师兄
 * 2017-12-09 22:53
 */
public class ResultVOUtil {

    public static ResultVo success(Object object) {
        ResultVo resultVO = new ResultVo();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVo error(Object object) {
        ResultVo resultVO = new ResultVo();
        resultVO.setData(object);
        resultVO.setCode(1);
        resultVO.setMsg("失败");
        return resultVO;
    }
}
