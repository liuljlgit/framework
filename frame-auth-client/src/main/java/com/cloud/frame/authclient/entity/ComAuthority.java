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
@ApiModel("ComAuthority")
public class ComAuthority extends BaseQuery {

	@ApiModelProperty("主键")
    @PrimaryKey
    private Long authId;

	@ApiModelProperty("权限名称")
    private String authName;

	@ApiModelProperty("允许通过路径，逗号分隔")
    private String permitUrls;

	@ApiModelProperty("禁止通过路径，逗号分隔")
    private String forbidUrls;

	@ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

	@ApiModelProperty("状态：正常/已删除")
    private Byte isEnable;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String AUTH_ID = "auth_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String AUTH_NAME = "auth_name";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String PERMIT_URLS = "permit_urls";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String FORBID_URLS = "forbid_urls";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String CREATE_TIME = "create_time";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String IS_ENABLE = "is_enable";


    public void andAuthId(Opt opt) {
        addConditGroup(AUTH_ID,opt);
    }

    public void andAuthId(Opt opt,Long authId) {
        addConditGroup(AUTH_ID,opt,authId);
    }

    public void andAuthId(Opt opt,List<Long> list) {
        addConditGroup(AUTH_ID,opt,list);
    }

    public void andAuthId(Opt opt,Long firstParam,Long secondParam) {
        addConditGroup(AUTH_ID,opt,firstParam,secondParam);
    }

    public void andAuthName(Opt opt) {
        addConditGroup(AUTH_NAME,opt);
    }

    public void andAuthName(Opt opt,String authName) {
        addConditGroup(AUTH_NAME,opt,authName);
    }

    public void andAuthName(Opt opt,List<String> list) {
        addConditGroup(AUTH_NAME,opt,list);
    }

    public void andAuthName(Opt opt,String firstParam,String secondParam) {
        addConditGroup(AUTH_NAME,opt,firstParam,secondParam);
    }

    public void andPermitUrls(Opt opt) {
        addConditGroup(PERMIT_URLS,opt);
    }

    public void andPermitUrls(Opt opt,String permitUrls) {
        addConditGroup(PERMIT_URLS,opt,permitUrls);
    }

    public void andPermitUrls(Opt opt,List<String> list) {
        addConditGroup(PERMIT_URLS,opt,list);
    }

    public void andPermitUrls(Opt opt,String firstParam,String secondParam) {
        addConditGroup(PERMIT_URLS,opt,firstParam,secondParam);
    }

    public void andForbidUrls(Opt opt) {
        addConditGroup(FORBID_URLS,opt);
    }

    public void andForbidUrls(Opt opt,String forbidUrls) {
        addConditGroup(FORBID_URLS,opt,forbidUrls);
    }

    public void andForbidUrls(Opt opt,List<String> list) {
        addConditGroup(FORBID_URLS,opt,list);
    }

    public void andForbidUrls(Opt opt,String firstParam,String secondParam) {
        addConditGroup(FORBID_URLS,opt,firstParam,secondParam);
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

    public void andIsEnable(Opt opt) {
        addConditGroup(IS_ENABLE,opt);
    }

    public void andIsEnable(Opt opt,Byte isEnable) {
        addConditGroup(IS_ENABLE,opt,isEnable);
    }

    public void andIsEnable(Opt opt,List<Byte> list) {
        addConditGroup(IS_ENABLE,opt,list);
    }

    public void andIsEnable(Opt opt,Byte firstParam,Byte secondParam) {
        addConditGroup(IS_ENABLE,opt,firstParam,secondParam);
    }

}