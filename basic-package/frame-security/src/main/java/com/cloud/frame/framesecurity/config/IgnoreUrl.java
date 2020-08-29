package com.cloud.frame.framesecurity.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 忽略鉴权配置
 * @author lijun
 */
@ConfigurationProperties(prefix = "oauth2.ignore")
@Data
public class IgnoreUrl {
    private List<String> uri;
}
