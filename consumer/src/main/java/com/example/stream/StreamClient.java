package com.example.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 定义接口，绑定INPUT和OUTPUT，配置中要有myInput和myoutput的destination，两者要一致，否则无法通信
 * @author xuan
 * @create 2018-06-02 19:20
 **/
public interface StreamClient {

    String INPUT = "myInput";

    String OUTPUT = "myOutput";

    String INPUT2 = "myInput2";

    String OUTPUT2 = "myOutput2";

    @Input(StreamClient.INPUT)
    SubscribableChannel input();

    @Output(StreamClient.OUTPUT)
    MessageChannel output();

    @Input(StreamClient.INPUT2)
    SubscribableChannel input2();

    @Output(StreamClient.OUTPUT2)
    MessageChannel output2();
}
