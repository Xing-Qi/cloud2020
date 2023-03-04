package com.atguigu.springcloud;

import com.atguigu.Myrule.MyselfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author Oliver
 * @create 2023-03-03 20:46
 */
@SpringBootApplication
@EnableEurekaClient
//指定服务器负载均衡规则
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyselfRule.class)
public class MainApp80 {
    public static void main(String[] args) {
        SpringApplication.run(MainApp80.class,args);
    }
}
