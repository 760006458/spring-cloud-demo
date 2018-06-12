package com.example.feign;

import com.example.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * FeignClient注解：指定服务名
 * GetMapping路径：要是全路径；rest风格传参，不要忘记PathVariable注解
 *
 * @author xuan
 * @create 2018-05-27 16:03
 **/
@FeignClient(name = "producer-service")
public interface ProducerClient {

    @GetMapping("/producer/findById/{id}")
    User findById(@PathVariable(name = "id") Long id);
}
