package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author xuan
 * @create 2018-05-15 16:35
 **/
@SpringBootApplication
@EnableEurekaServer
public class EurekaStarter {

    public static void main(String[] args) {
        SpringApplication.run(EurekaStarter.class,args);
    }
}
