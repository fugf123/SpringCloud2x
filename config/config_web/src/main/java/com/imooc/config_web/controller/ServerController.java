package com.imooc.config_web.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 廖师兄
 * 2017-12-10 20:37
 */
@RestController
@RefreshScope
public class ServerController {

    @GetMapping("/msg")
    public String msg() {
        try {

            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("aaaaa");
        return "this is product' msg";
    }
}
