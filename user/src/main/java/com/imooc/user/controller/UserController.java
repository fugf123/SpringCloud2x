package com.imooc.user.controller;

import com.imooc.user.dataobject.UserInfo;
import com.imooc.user.service.UserService;
import com.imooc.user.utils.CookieUtil;
import com.imooc.user.utils.ResultVOUtil;
import com.imooc.user.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService _userService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/login")
    public ResultVo loginUser(HttpServletResponse response, String userName, String pwd){
        boolean login = _userService.loginUser(userName,pwd);
        if(!login){
            ResultVOUtil.error(null);
        }
        redisTemplate.opsForValue().set("userName",userName,2000, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set("pwd",pwd);
        CookieUtil.setCookie(response,"userName",userName,2000);
        CookieUtil.setCookie(response,"pwd",pwd,2000);
        return ResultVOUtil.success(null);
    }
}
