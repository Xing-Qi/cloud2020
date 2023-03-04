package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Oliver
 * @create 2023-03-05 0:01
 */
@Component
public class MyLB implements LoaderBalancer{
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    public final int getAndIncrement(){
        int current;
        int next;
        do{
            current = this.atomicInteger.get();//获取当前请求次数
            next = current >= 2147483647 ? 0: current + 1;
        }while (!this.atomicInteger.compareAndSet(current,next));//compareAndSet自旋
        return next;
    }

    @Override
    /**
     * 获取服务下标
     */
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
