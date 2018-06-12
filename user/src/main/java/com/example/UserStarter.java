package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * @author xuan
 * @create 2018-06-07 13:33
 **/
//@SpringBootApplication
//@EnableDiscoveryClient  //疑惑：该注解的作用，好像不加也不影响啥，哪怕template.getForEntity("http://PRODUCER-SERVICE/producer/findById/1", User.class)都用不上
//@EnableCircuitBreaker   //服务熔断
@SpringCloudApplication   //等价于上边3个注解
@EnableFeignClients
@EnableHystrixDashboard
public class UserStarter {

    public static void main(String[] args) {
        SpringApplication.run(UserStarter.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
