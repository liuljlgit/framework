package com.cloud.frame.authclient.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Liulj
 * @version v 0.1 2019/11/6 14:44
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("登录信息")
public class LoginReq {

    @ApiModelProperty(value="用户名",required = true)
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty(value="登录模式",example = "password")
    private String grant_type;

    @ApiModelProperty(value="资源范围",example = "server")
    private String scope;

    @ApiModelProperty(value = "客户端id",example = "client_1")
    private String client_id;

    @ApiModelProperty(value="客户端秘钥",example = "123456")
    private String client_secret;

    @ApiModelProperty(value = "应用名称",example = "bus-yd-server")
    private String applicationName;

    @ApiModelProperty(value="需要刷新的token值，刷新token的时候此值有效")
    private String refresh_token;

}
