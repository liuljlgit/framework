package com.cloud.frame.framesecurity.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Liulj
 * @version v 0.1 2019/11/26 11:01
 */
@ConfigurationProperties(prefix = "spring.application")
@Data
public class ServerConfig {

    private String name;

}
