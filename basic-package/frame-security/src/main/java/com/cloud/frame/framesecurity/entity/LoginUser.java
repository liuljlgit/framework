package com.cloud.frame.framesecurity.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Liulj
 * @version v 0.1 2019/11/14 18:04
 */
@Data
@ApiModel("登录用户")
public class LoginUser {

    @ApiModelProperty("主键")
    private Long userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("电话")
    private String account;

    @ApiModelProperty("状态：正常/禁用")
    private Byte status;

    @ApiModelProperty("创建时间")
    private Date createTime;

}
