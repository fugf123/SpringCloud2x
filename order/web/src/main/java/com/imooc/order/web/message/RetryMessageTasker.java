package com.imooc.order.web.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.SQLOutput;

public class RetryMessageTasker {
    @Autowired
    private RabbitSender rabbitSender;

    /**
     * 3秒之后启动定时任务，每10秒执行一次
     */
    @Scheduled(initialDelay = 3000,fixedDelay = 10000)
    public void reSend(){
        System.out.println("定时");
    }

}
