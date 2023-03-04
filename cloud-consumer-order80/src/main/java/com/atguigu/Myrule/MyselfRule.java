package com.atguigu.Myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Oliver
 * @create 2023-03-04 23:12
 */
@Configuration
public class MyselfRule {
    @Bean
    public IRule myRule(){
        return new RandomRule();//定义为随机
    }
}
