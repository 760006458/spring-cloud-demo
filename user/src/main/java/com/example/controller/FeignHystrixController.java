package com.example.controller;

import com.example.entity.User;
import com.example.feign.ProducerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于测试feign的hystrix，浏览器访问controller接口有时需要熔断保护；
 * 同理，feign调用其他服务接口有时也需要熔断保护
 *
 * @author xuan
 * @create 2018-06-11 18:57
 **/
@RestController
public class FeignHystrixController {

    @Autowired
    private ProducerClient producerClient;

    @GetMapping("/feign-hystrix")
    public String feignHystrixTest(@RequestParam("number") int number) {
        return producerClient.feignHystrixTest(number);
    }
}
