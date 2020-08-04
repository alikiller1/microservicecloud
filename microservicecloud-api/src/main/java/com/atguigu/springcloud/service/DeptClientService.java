package com.atguigu.springcloud.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atguigu.springcloud.entities.Dept;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 *
 * @Description: 修改microservicecloud-api工程，根据已经有的DeptClientService接口

新建

一个实现了FallbackFactory接口的类DeptClientServiceFallbackFactory
 * @author zzyy
 * @date 2018年4月21日
 */
@FeignClient(value = "MICROSERVICECLOUD-DEPT")
//@FeignClient(value = "MICROSERVICECLOUD-DEPT",fallbackFactory=DeptClientServiceFallbackFactory.class)
public interface DeptClientService
{
	/*	@HystrixCommand(
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
    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") long id);
    
  
    @RequestMapping(value = "/dept/get2/{id}", method = RequestMethod.GET)
    public Dept get2(@PathVariable("id") long id);

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list();

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(Dept dept);
}
