package com.example;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;

/**
 * @author xuan
 * @create 2018-05-28 16:04
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigStarter {

    public static void main(String[] args) {
        SpringApplication.run(ConfigStarter.class,args);
    }

    /*@Bean
    public Queue helloQueue(){
        return new Queue("hello");
    }*/
}
