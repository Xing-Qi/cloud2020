package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoaderBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

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
    @Resource
    private LoaderBalancer loaderBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

    //客户端用浏览器是get请求，但是底层实质发送post调用服务端8001
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        /*(url, requestMap, ResponseBean.class)这三个参数分别代表
        REST请求地址、请求参数、HTTP响应转换被转换成的对象类型。*/

        return restTemplate.postForObject(PAYMENT_SERVER_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        //      restTemplate.getForObject返回对象为响应体中数据转化成的对象，基本上可以理解为Json
        return restTemplate.getForObject(PAYMENT_SERVER_URL + "/payment/get/" + id, CommonResult.class);
    }
    @GetMapping("/consumer/payment/getForEntity/{id}")
   public CommonResult<Payment> getPaymentForEntity(@PathVariable("id") Long id){
//       restTemplate.getForEntity返回对象为ResponseEntity对象，包含了响应中的一些重要信息，比如响应头、响应状态码、响应体等
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_SERVER_URL + "/payment/get/" + id, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommonResult(444,"操作失败");
        }
    }
    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances==null||instances.size()<=0){
            return null;
        }
        ServiceInstance serviceInstance = loaderBalancer.instance(instances);
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri+"/payment/lb", String.class);
    }

}
