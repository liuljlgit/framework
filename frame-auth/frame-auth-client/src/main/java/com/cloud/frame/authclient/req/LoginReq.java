package com.cloud.frame.authclient.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Liulj
 * @version v 0.1 2019/11/6 15:20
 */
@Data
@ApiModel("登录请求")
public class LoginReq {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

}
