package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author Oliver
 * @create 2023-03-05 0:00
 */
public interface LoaderBalancer {
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
