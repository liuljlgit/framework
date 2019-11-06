package com.cloud.frame.authclient.entity;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.cloud.ftl.ftlbasic.webEntity.BaseQuery;
import com.cloud.ftl.ftlbasic.enums.Opt;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.cloud.ftl.ftlbasic.annotation.PrimaryKey;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("ComUser")
public class ComUser extends BaseQuery {

	@ApiModelProperty("")
    @PrimaryKey
    private Long userId;

	@ApiModelProperty("账号（手机号）")
    private String account;

	@ApiModelProperty("用户名")
    private String userName;

	@ApiModelProperty("密码（bcrypt加密）")
    private String password;

	@ApiModelProperty("状态：正常/禁用")
    private Byte status;

	@ApiModelProperty("")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String USER_ID = "user_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String ACCOUNT = "account";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String USER_NAME = "user_name";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String PASSWORD = "password";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String STATUS = "status";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String CREATE_TIME = "create_time";


    public void andUserId(Opt opt) {
        addConditGroup(USER_ID,opt);
    }

    public void andUserId(Opt opt,Long userId) {
        addConditGroup(USER_ID,opt,userId);
    }

    public void andUserId(Opt opt,List<Long> list) {
        addConditGroup(USER_ID,opt,list);
    }

    public void andUserId(Opt opt,Long firstParam,Long secondParam) {
        addConditGroup(USER_ID,opt,firstParam,secondParam);
    }

    public void andAccount(Opt opt) {
        addConditGroup(ACCOUNT,opt);
    }

    public void andAccount(Opt opt,String account) {
        addConditGroup(ACCOUNT,opt,account);
    }

    public void andAccount(Opt opt,List<String> list) {
        addConditGroup(ACCOUNT,opt,list);
    }

    public void andAccount(Opt opt,String firstParam,String secondParam) {
        addConditGroup(ACCOUNT,opt,firstParam,secondParam);
    }

    public void andUserName(Opt opt) {
        addConditGroup(USER_NAME,opt);
    }

    public void andUserName(Opt opt,String userName) {
        addConditGroup(USER_NAME,opt,userName);
    }

    public void andUserName(Opt opt,List<String> list) {
        addConditGroup(USER_NAME,opt,list);
    }

    public void andUserName(Opt opt,String firstParam,String secondParam) {
        addConditGroup(USER_NAME,opt,firstParam,secondParam);
    }

    public void andPassword(Opt opt) {
        addConditGroup(PASSWORD,opt);
    }

    public void andPassword(Opt opt,String password) {
        addConditGroup(PASSWORD,opt,password);
    }

    public void andPassword(Opt opt,List<String> list) {
        addConditGroup(PASSWORD,opt,list);
    }

    public void andPassword(Opt opt,String firstParam,String secondParam) {
        addConditGroup(PASSWORD,opt,firstParam,secondParam);
    }

    public void andStatus(Opt opt) {
        addConditGroup(STATUS,opt);
    }

    public void andStatus(Opt opt,Byte status) {
        addConditGroup(STATUS,opt,status);
    }

    public void andStatus(Opt opt,List<Byte> list) {
        addConditGroup(STATUS,opt,list);
    }

    public void andStatus(Opt opt,Byte firstParam,Byte secondParam) {
        addConditGroup(STATUS,opt,firstParam,secondParam);
    }

    public void andCreateTime(Opt opt) {
        addConditGroup(CREATE_TIME,opt);
    }

    public void andCreateTime(Opt opt,Date createTime) {
        addConditGroup(CREATE_TIME,opt,createTime);
    }

    public void andCreateTime(Opt opt,List<Date> list) {
        addConditGroup(CREATE_TIME,opt,list);
    }

    public void andCreateTime(Opt opt,Date firstParam,Date secondParam) {
        addConditGroup(CREATE_TIME,opt,firstParam,secondParam);
    }

}