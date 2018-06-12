package com.example.receiver;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xuan
 * @create 2018-05-30 10:25
 **/
@Component
public class Receiver {

    /**
     * 注：RabbitMQ的exchange就是kafka和activemq中的topic
     *
     * @param msg
     */
//    @RabbitListener(queues = "hello") //该方案需要在spring容器中创建一个name=hello的Queue
    @RabbitListener(bindings = {@QueueBinding(value = @Queue("myQueue"),key = "computer",exchange = @Exchange("myExchange"))})
    public void handlerComputer(String msg) {
        System.out.println("computer收到消息：" + msg);
    }

    @RabbitListener(bindings = {@QueueBinding(value = @Queue("myQueue"),key = "fruit",exchange = @Exchange("myExchange"))})
    public void handlerFruit(String msg) {
        System.out.println("fruit收到消息：" + msg);
    }
}
