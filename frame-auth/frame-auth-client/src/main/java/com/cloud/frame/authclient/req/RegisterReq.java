package com.cloud.frame.authclient.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Liulj
 * @version v 0.1 2019/11/6 15:22
 */
@Data
@ApiModel("注册")
public class RegisterReq {

    @NotBlank
    @ApiModelProperty("账号(手机号)")
    private String account;

    @NotBlank
    @ApiModelProperty("用户名")
    private String userName;

    @NotBlank
    @ApiModelProperty("密码")
    private String password;

}
