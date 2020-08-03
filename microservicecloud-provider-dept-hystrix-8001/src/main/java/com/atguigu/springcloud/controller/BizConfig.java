package com.atguigu.springcloud.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**   
 * LL
 * 2020年3月12日 下午4:29:01
 */

@Data
@Component
@ConfigurationProperties(prefix = "biz")
public class BizConfig {
    private String key;
    private Long refresh;
}