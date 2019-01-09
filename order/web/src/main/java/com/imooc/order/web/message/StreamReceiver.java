package com.imooc.order.web.message;

import com.imooc.order.web.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;


/***
 * Stream信息接收端
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

    @StreamListener(StreamClient.INPUT)
    public void process(String message) {
       // log.info("Message {}",message);
    }

    /**
     * 接收对象类型消息
     * @param message
     */
//    @StreamListener(StreamClient.INPUT)
//    @SendTo(StreamClient.INPUT2) //执行完之后将消息发送给谁
//    public String processObj(ProductInfo message) {
//        log.info("Message: {}",message);
//        return "ok"; //发送给INPUT2的消息
//    }
//
//    @StreamListener(StreamClient.INPUT2)
//    public void process2(String message) {
//        log.info("Message2: {}",message);
//    }
}
