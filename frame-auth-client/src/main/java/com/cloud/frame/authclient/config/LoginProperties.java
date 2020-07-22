package com.cloud.frame.authclient.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 客户端配置
 * @author lijun
 */
@ConfigurationProperties(prefix = "oauth2")
@Data
@Component
public class LoginProperties {
    //登录模式
    private String grantType = "password";

    @ApiModelProperty(value="资源范围",example = "server")
    private String scope = "server";

    @ApiModelProperty(value = "客户端id",example = "client_1")
    private String clientId = "client_1";

    @ApiModelProperty(value="客户端秘钥",example = "123456")
    private String clientSecret = "123456";

    @ApiModelProperty(value = "应用名称")
    private String applicationName;
}
