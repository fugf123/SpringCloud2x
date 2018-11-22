package com.imooc.apigatway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatwayController {
    @GetMapping("/print")
    public String print(){
        String gat = "gatway";
        System.out.println(gat);
        return gat;
    }
}
