package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author Oliver
 * @create 2023-03-03 21:00
 */
@RestController
@Slf4j
public class OrderController {
    //    private static final String PAYMENT_SERVER_URL = "http://localhost:8001";
    private static final String PAYMENT_SERVER_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    //客户端用浏览器是get请求，但是底层实质发送post调用服务端8001
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        /*(url, requestMap, ResponseBean.class)这三个参数分别代表
        REST请求地址、请求参数、HTTP响应转换被转换成的对象类型。*/
        return restTemplate.postForObject(PAYMENT_SERVER_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_SERVER_URL + "/payment/get/" + id, CommonResult.class);
    }
}
