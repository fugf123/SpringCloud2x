package com.imooc.order.web.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface StreamClient {

    String  INPUT = "myMessageInput";  //input和outPut不能重名
    String  OUTPUT = "myMessageOutput";
    String  INPUT2 = "myMessageInput2";
    String  OUTPUT2 = "myMessageOutput2";
    @Input(StreamClient.INPUT)
    SubscribableChannel input();

//    @Output(StreamClient.OUTPUT)
//    MessageChannel output();

//    @Input(StreamClient.INPUT2)
//    SubscribableChannel input2();
//
//    @Output(StreamClient.INPUT2)
//    MessageChannel output2();



}
