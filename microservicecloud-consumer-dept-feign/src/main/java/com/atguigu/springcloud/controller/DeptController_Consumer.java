package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptClientService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ZeroV on 2018/11/6.
 */
@RestController
public class DeptController_Consumer {

    @Autowired
    DeptClientService service;
    
	/*  @HystrixCommand(
			commandProperties = {
					//10秒内达到5个请求，且50%（默认）以上的失败，则启动熔断
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
					//是否启用熔断，默认true
					@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
					//是否强制关闭熔断,默认false
	//    				@HystrixProperty(name = "circuitBreaker.forceClosed", value = "true"),
					//超时时间
	//    				@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
					
			})*/
    @RequestMapping(value = "/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id)
    {
		
		  System.out.println("request...."); 
		  return this.service.get(id);
		 
    }

    @RequestMapping(value = "/consumer/dept/list")
    public List<Dept> list()
    {
        return this.service.list();
    }

    @RequestMapping(value = "/consumer/dept/add")
    public Object add(Dept dept)
    {
        return this.service.add(dept);
    }
    
    @HystrixCommand(
    		commandProperties = {
    				//10秒内达到5个请求，且50%（默认）以上的失败，则启动熔断
    				@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
    				//是否启用熔断，默认true
    				@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
    				//是否强制关闭熔断,默认false
//    				@HystrixProperty(name = "circuitBreaker.forceClosed", value = "true"),
    				//超时时间
//    				@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
    				
    		})
    @RequestMapping("test")
    public String test(String name) throws Exception {
    	System.out.println("request...");
    	if("a".equalsIgnoreCase(name)) {
    		throw new Exception("aaa");
    	}
    	return "test";
    }

}
