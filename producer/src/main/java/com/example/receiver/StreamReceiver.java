package com.example.receiver;

import com.example.entity.User;
import com.example.stream.StreamClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * BeanDefinitionStoreException: Invalid bean definition with name 'myMessage' defined
 * in com.example.stream.StreamClient: bean definition with this name already exists
 *
 * @author xuan
 * @create 2018-06-02 19:24
 **/
@Component
@EnableBinding(StreamClient.class)
public class StreamReceiver {

    private Logger logger = LoggerFactory.getLogger(StreamReceiver.class);

    /**
     * SendTo回复时，不能使用同一个INPUT通道
     * @param user
     * @return
     */
    @StreamListener(StreamClient.INPUT)
    @SendTo(StreamClient.OUTPUT2)
    public String process(User user) {
        logger.info("【Rabbit-stream】收到消息：{}", user);
        return "我已收到消息";
    }
}
