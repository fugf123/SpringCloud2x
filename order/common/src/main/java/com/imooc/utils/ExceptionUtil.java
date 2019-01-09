package com.imooc.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * 异常处理工具类 数据库存用异常信息用longtxt类型
 */
public class ExceptionUtil {

    /**
     * 返回错误信息字符串
     *
     * @param ex Exception
     * @return 错误信息字符串
     */
    public static String getThrowableMessage(Throwable ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }
}
