package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Oliver
 * @create 2023-03-01 16:33
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("******插入操作返回结果：" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功id= " + payment.getId() + "serverPort: " + serverPort, result);
        } else {
            return new CommonResult(400, "插入数据库失败,serverPort: " + serverPort, null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("******查询结果:{}", payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort: " + serverPort + " id：" + id, payment);
        } else {
            return new CommonResult(400, "查询失败serverPort: " + serverPort + " id: " + id, null);
        }
    }
}
