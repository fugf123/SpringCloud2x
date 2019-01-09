package com.imooc.order.web.controller;

import com.imooc.product.client.client.ProductClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/***
 * RefreshScope 刷新配置  哪个地方用到就在哪个地方刷新
 */
@RestController
@RefreshScope
@DefaultProperties(defaultFallback = "defaultFallBack")//默认熔断提示
public class OrderController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${env}")
    private String env1;

    @GetMapping("/msg")
    public String msg() {
        return "this is product' msg"+env1;
    }

    //Hystrix 两种配置方式 1.代码配置  2.配置文件配置

    @HystrixCommand(fallbackMethod="fallback",commandProperties={
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"),//配置超时时间
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//设置熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//配置一个请求最大线程数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//配置休眠时间
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//配置熔断开启最大百分比

        }
        )  //抛出异常可以触发服务熔断
    @GetMapping("/msg1")
    public String method1() {
        //d第一种方式RestRemplate 写死url
        RestTemplate restTemplate = new RestTemplate();
        String url =  restTemplate.getForObject("http://localhost:8083/msg",String.class);
        System.out.println(url);
        return url;
    }

    private String fallback(){
        return "太拥挤啦，请稍后再试";
    }

    private String defaultFallBack(){
        return "默认提示:太拥挤啦，请稍后再试";
    }


    @GetMapping("/msg2")
    public String method2() {
        //第二种方式 利用LoadBalancerClient获得url
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String url1 = String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort())+"/msg";
        RestTemplate restTemplate = new RestTemplate();
        String url =  restTemplate.getForObject(url1,String.class);
        return url;
    }

    @GetMapping("/msg3")
    public String method3() {
        //第三种方式
        String url = restTemplate.getForObject("http://PRODUCT/msg",String.class);
        System.out.println(url);
        //LoadBalancerClient ribbon负载均衡
        //1.服务发现 2.服务选择规则  3. 服务监听   ServerList IRule  ServerListFilter
        //主要流程 1.首先通过ServerList 获得所有的可用服务列表 2.然后通过ServerListFilter过滤掉一部分地址
        // 3.通过IRule选择一个实例作为最终的服务e

        //真正是使用的DynamicServerListLoadBalancer负载均衡规则
        return url;
    }
    @HystrixCommand
    @GetMapping("/msg4")
    public String method4(int number) {

        if(number%2 == 0){
            return "ok";
        }
        //第四种 使用feign
         String msg = productClient.productMsg();
        return msg+"4";
    }

    @GetMapping("/create")
    public String create() {
        //d第一种方式RestRemplate 写死url
        /*RestTemplate restTemplate = new RestTemplate();
        String url =  restTemplate.getForObject("http://localhost:8081/msg",String.class);
        System.out.println(url);*/

        //第二种方式 利用LoadBalancerClient获得url
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String url1 = String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort())+"/msg";
        RestTemplate restTemplate = new RestTemplate();
        String url =  restTemplate.getForObject(url1,String.class);
        //第三种方式
       /* String url = restTemplate.getForObject("http://PRODUCT/msg",String.class);*/
        System.out.println(url);
       //LoadBalancerClient ribbon负载均衡
        //1.服务发现 2.服务选择规则  3. 服务监听   ServerList IRule  ServerListFilter
        //主要流程 1.首先通过ServerList 获得所有的可用服务列表 2.然后通过ServerListFilter过滤掉一部分地址
        // 3.通过IRule选择一个实例作为最终的服务e

        //真正是使用的DynamicServerListLoadBalancer负载均衡规则
        //第四种
      //  String msg = productClient.productMsg();

        return url;
    }
}
