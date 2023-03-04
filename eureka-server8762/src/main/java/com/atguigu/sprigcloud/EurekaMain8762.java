package com.atguigu.sprigcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Oliver
 * @create 2023-03-04 9:41
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaMain8762 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain8762.class,args);
    }
}
