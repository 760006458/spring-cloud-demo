package com.example.sender;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xuan
 * @create 2018-06-01 18:02
 **/
public class Sender extends SuperClassTest {

    @Autowired
    private AmqpTemplate template;

    @Test
    public void sendTest() {
        template.convertAndSend("myExchange", "computer", "你好！");
    }
}
