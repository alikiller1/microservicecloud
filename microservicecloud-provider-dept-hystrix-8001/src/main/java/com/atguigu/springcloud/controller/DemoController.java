package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;


/**   
 * LL
 * 2020年3月12日 下午4:26:34
 */

@RefreshScope
@RestController
public class DemoController {

    @Autowired
    private ContextRefresher contextRefresher;

    @Autowired
    private BizConfig bizConfig;

    @Autowired
    private ValueConfig valueConfig;
    
    @Qualifier("commonConfig1")
    @Autowired
    private CommonConfig commonConfig1;
    
    @Qualifier("commonConfig2")
    @Autowired
    private CommonConfig commonConfig2;

    @Value("${rest.uuid}")
    private String uuid;

    @GetMapping("/show")
    public String show() {
        JSONObject res = new JSONObject();
        res.put("biz", JSONObject.toJSONString(bizConfig));
        res.put("uuid", valueConfig.getUuid());
        res.put("no-refresh", uuid);
        System.out.println(commonConfig1.getUuid());
        System.out.println(commonConfig2.getUuid());
        res.put("common-uuid1",commonConfig1.getUuid());
        res.put("common-uuid2",commonConfig2.getUuid());
        return res.toJSONString();
    }

    @GetMapping("/fresh")
    public String refresh() {
        contextRefresher.refresh();
        return show();
    }
	
	@EventListener
	public void envListener(EnvironmentChangeEvent event) {
	    System.out.println("conf change: " + event);
	}
}
