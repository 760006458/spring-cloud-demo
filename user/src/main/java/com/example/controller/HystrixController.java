package com.example.controller;

import com.example.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 用于测试hystrix的服务降级、服务熔断等功能，解决雪崩效应。此处业务没有逻辑，纯粹瞎调测试
 *
 * @author xuan
 * @create 2018-06-10 12:33
 **/
@RestController
@RequestMapping("/hystrix")
@DefaultProperties(defaultFallback = "myFallback")  //设置全局的hystrix异常回调函数
public class HystrixController {

    @Autowired
    private RestTemplate template;

    @RequestMapping("/getUserList")
//    @HystrixCommand(fallbackMethod = "myFallback")
    //注：去HystrixCommandProperties中找。业务：开户、充值、提现、代扣等业务比较耗时，超时时间需要设置的更长一些
    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),  //本注解及下边3个注解，用于设置hystrix服务熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String getUserList(@RequestParam("number") int number) {
        //用于模拟服务熔断，如果请求参数number是偶数，则该服务调用成功；
        //否则进行下边的rest调用，producer服务先不启动，故意让它报错，于是触发降级服务
        if (number % 2 == 0) {
            return "success";
        }
        ResponseEntity<User> entity = template.getForEntity("http://PRODUCER-SERVICE/producer/findById/1", User.class);
//        ResponseEntity<User> entity = template.getForEntity("http://localhost:8080/producer/findById/1", User.class);
        return entity.getBody().toString();
    }

    /**
     * 用于测试hystrix的yml配置方案
     * @param number
     * @return
     */
    @RequestMapping("/getUserList2")
    @HystrixCommand
    public String getUserList2(@RequestParam("number") int number){
        if (number % 2 == 0) {
            return "success";
        }
        throw new RuntimeException();
    }

    /**
     * 本类内部使用，所以私有/共有无所谓，本方法其实就是服务降级后调用的方法。
     * 即原服务出问题后，由于hystrix的降级设置，从而将主逻辑降级为本逻辑
     * @return 要求降级逻辑和原逻辑方法返回值必须一致！
     */
    private String myFallback() {
        return "太拥挤了，稍后再试~~";
    }
}
