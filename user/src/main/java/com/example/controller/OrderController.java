package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

/**
 * 订单controller，用于测试zuul网关的权限认证
 *
 * @author xuan
 * @create 2018-06-09 19:46
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    /**
     * 创建订单，只能买家访问
     *
     * @return
     */
    @RequestMapping("/create")
    public String createOrder() {
        logger.info("【订单创建】，{}", new Date().toString());
        return "订单创建成功";
    }

    /**
     * 完结订单，只能卖家访问
     *
     * @return
     */
    @RequestMapping("/finish")
    public String finishOrder() {
        logger.info("【订单完结】，{}", new Date().toString());
        return "订单完结成功";
    }
}
