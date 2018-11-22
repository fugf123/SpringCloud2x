package com.imooc.product.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 廖师兄
 * 2017-12-10 20:37
 */
@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("123333333");
        return "this is product' msg1";
    }
}
