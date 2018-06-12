package com.example.receiver;

import com.example.stream.StreamClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @author xuan
 * @create 2018-06-06 10:09
 **/
@Component
@EnableBinding(StreamClient.class)
public class CallbackReceiver {

    private Logger logger = LoggerFactory.getLogger(CallbackReceiver.class);

    @StreamListener(StreamClient.INPUT2)
    public void myCallback(String msg) {
        logger.info("【收到mq回复的消息】，{}", msg);
    }
}
