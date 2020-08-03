package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**   
 * LL
 * 2020年3月12日 下午7:07:44
 */

@RefreshScope
@Configuration
public class ConfigClass {
	@Autowired
	private ValueConfig valueConfig;
	
	
	@Bean
	public CommonConfig commonConfig1() {
		CommonConfig c=new CommonConfig();
		c.setUuid(valueConfig.getUuid());
		return c;
	} 
	
	@RefreshScope
	@Bean
	public CommonConfig commonConfig2() {
		CommonConfig c=new CommonConfig();
		c.setUuid(valueConfig.getUuid());
		return c;
	} 
}
