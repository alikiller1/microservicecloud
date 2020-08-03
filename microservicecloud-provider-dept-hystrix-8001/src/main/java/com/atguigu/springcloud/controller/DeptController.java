package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class DeptController
{
    @Autowired
    private DeptService service = null;

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    //一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
//    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public Dept get(@PathVariable("id") Long id)
    {
    	System.out.println("request----");
    	

//        Dept dept = this.service.get(id);
    	 Dept dept=null;
//    	 Dept dept=new Dept();
//    	 dept.setDb_source("01");
//    	 dept.setDeptno(1l);
//    	 dept.setDname("aaa");
        if (null == dept) {
            throw new RuntimeException("该ID：" + id + "没有没有对应的信息");
        }

        return dept;
    }

    public Dept processHystrix_Get(@PathVariable("id") Long id)
    {
        return new Dept().setDeptno(id).setDname("该ID：" + id + "没有没有对应的信息,null--@HystrixCommand")
                .setDb_source("no this database in MySQL!");
    }
    
    @HystrixCommand
    @RequestMapping(value = "/dept/get2/{id}", method = RequestMethod.GET)
    public Dept get2(@PathVariable("id") Long id)
    {
    	System.out.println("request----");
//        Dept dept = this.service.get(id);
    	 Dept dept=null;
//    	 Dept dept=new Dept();
//    	 dept.setDb_source("01");
//    	 dept.setDeptno(1l);
//    	 dept.setDname("aaa");
        if (null == dept) {
            throw new RuntimeException("该ID：" + id + "没有没有对应的信息");
        }

        return dept;
    }
    
    @RequestMapping("/save")
    public boolean add(Dept dept,String dname) {
    	System.out.println("abc");
    	return service.add(dept);
    }
}