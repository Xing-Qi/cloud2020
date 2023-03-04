package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author Oliver
 * @create 2023-03-04 22:09
 */
@RestController
@Slf4j
public class OrderController {
    //调用服务的地址
    private static final String INVOKE_URL="http://consul-provider-payment";
    @Resource
    private RestTemplate restTemplate;
    @GetMapping("consumer/payment/consul")
    public String paymentInfo(){
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
        log.info("消费者调用支付服务(consule)--->result:" + result );
        return result;
    }

}
