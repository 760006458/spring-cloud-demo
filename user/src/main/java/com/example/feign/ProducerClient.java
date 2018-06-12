package com.example.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 本接口用于测试feign-hystrix服务降级
 * 注：理论上FeignClient由服务提供者维护比较好，客户端引入其jar包依赖。
 * 但若如上所述，需要注意包扫描范围，否则ProducerClientFallback的@Component注解扫描不到，
 * 可以在启动类上用@ComponentScan扩大扫描范围.
 *
 * @author xuan
 * @create 2018-06-11 18:48
 **/
@FeignClient(name = "producer-service", fallback = ProducerClient.ProducerClientFallback.class)
public interface ProducerClient {

    @GetMapping("/producer/feign-hystrix")
    String feignHystrixTest(@RequestParam("number") int number);

    @Component
    class ProducerClientFallback implements ProducerClient {

        @Override
        public String feignHystrixTest(@RequestParam("number") int number) {
            return "feign调用出问题，原服务被降级了";
        }
    }
}
