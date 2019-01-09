package com.imooc.utils;

import java.util.Arrays;
import java.util.List;

/**
 * @author 0
 * 公用的工具类
 */

public class CommonsUtil {
    /** 错误码 */
    public static final String ERROR = "-1";

    /** redis过期时间 目前设置10秒*/
    public static final Long REDIS_TIME = 10L;

    /** 上传文件格式校验*/
    public static final List ALLOW_ADDFILE_SUFFIX= Arrays.asList(".avi",".mp4",".3gp",".rmvb",".ppt",".pdf");

    /** 文件上传路径 */
    public static final String WEB_FILE_UPLOAD_PATH = "/file/";

}
