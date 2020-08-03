package com.atguigu.springcloud.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;

/**   
 * LL
 * 2020年3月12日 下午4:27:11
 */

@Data
@RefreshScope
@Component
public class ValueConfig {
    @Value("${rest.uuid}")
    private String uuid;
}

