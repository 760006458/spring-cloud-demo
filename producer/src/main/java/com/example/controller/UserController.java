package com.example.controller;

import com.example.dao.UserRepository;
import com.example.entity.User;
import com.example.stream.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * RefreshScope注解作用：消息总线自动刷新配置使用
 *
 * @author xuan
 * @create 2018-05-14 19:34
 **/
@RestController
@RefreshScope
public class UserController {

    /**
     * 用于测试消息总线的配置动态刷新
     */
    @Value("${from}")
    private String from;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StreamClient streamClient;

//    @Autowired
//    private Source source;    //不能注入Source

    /**
     * 注：使用getOne(Long id)方法虽然也能查出数据，但写回时报错
     * No serializer found for class org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer
     *
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public User findById(@PathVariable Long id) {
        return userRepository.findOne(id);
    }

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        return from;
    }

    /**
     * spring cloud stream发送消息
     */
    @GetMapping("/sendMessage")
    public void sendMessage() {
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        streamClient.output().send(MessageBuilder.withPayload(user).build());
    }

    /**
     * 用于测试user服务中的feign-hystrix
     * @param number
     * @return
     */
    @GetMapping("/feign-hystrix")
    public String feignHystrixTest(@RequestParam("number") int number) {
        if (number % 2 == 0) {
            return "原服务正常返回";
        }
        throw new RuntimeException("原服务异常");
    }
}
