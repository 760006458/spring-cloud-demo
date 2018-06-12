package com.example.controller;

import com.example.entity.User;
import com.example.feign.ProducerClient;
import com.example.stream.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author xuan
 * @create 2018-05-14 21:26
 **/
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StreamClient streamClient;

//    @Autowired
//    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private ProducerClient producerClient;

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable Integer id) {
        return restTemplate.getForEntity("http://PRODUCER-SERVICE/producer/findById/1", User.class).getBody();
//        return producerClient.findById((long) id);
    }

    @GetMapping("/hello")
    public String hello() {
        String result = restTemplate.getForObject("http://PRODUCER-SERVICE/producer/hello", String.class);
        return result;
    }

    /*@GetMapping("/hello")
    public String hello() {
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance instance = loadBalancerClient.choose("PRODUCER-SERVICE");
        String url = String.format("http://%s:%s/hello", instance.getHost(), instance.getPort());   //不行，getHost得到的是xuan，而不是ip
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }*/

    /**
     * spring cloud stream发送消息:
     * 给producer发消息，producer收到消息后回执消息，consumer的receiver再次接受回执消息。
     * 测试发现：之后断点模式下，偶尔成功，一般都会报错：xxx通道没有订阅者。
     */
    @GetMapping("/sendMessage")
    public void sendMessage() {
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        streamClient.output().send(MessageBuilder.withPayload(user).build());
    }
}
