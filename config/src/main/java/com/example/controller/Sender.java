package com.example.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RabbitMQ的测试Controller
 *
 * @author xuan
 * @create 2018-05-30 10:28
 **/
/*
@RestController
public class Sender {

    @Autowired
    private AmqpTemplate template;

    @GetMapping("/hello")
    public void sendStr() {
        template.convertAndSend("hello","你好！");
    }
}*/
