package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;

/**
 * @author Oliver
 * @create 2023-03-01 16:28
 */
public interface PaymentService {
     int create(Payment payment);
     Payment getPaymentById(long id);
}
