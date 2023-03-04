package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Oliver
 * @create 2023-03-04 1:02
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaMain8761 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain8761.class,args);
    }
}
