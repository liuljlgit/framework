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
@ApiModel("ComAuthorityRole")
public class ComAuthorityRole extends BaseQuery {

	@ApiModelProperty("主键")
    @PrimaryKey
    private Long arId;

	@ApiModelProperty("权限主键")
    private Long authId;

	@ApiModelProperty("角色主键")
    private Long roleId;

	@ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String AR_ID = "ar_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String AUTH_ID = "auth_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String ROLE_ID = "role_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String CREATE_TIME = "create_time";


    public void andArId(Opt opt) {
        addConditGroup(AR_ID,opt);
    }

    public void andArId(Opt opt,Long arId) {
        addConditGroup(AR_ID,opt,arId);
    }

    public void andArId(Opt opt,List<Long> list) {
        addConditGroup(AR_ID,opt,list);
    }

    public void andArId(Opt opt,Long firstParam,Long secondParam) {
        addConditGroup(AR_ID,opt,firstParam,secondParam);
    }

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

    public void andRoleId(Opt opt) {
        addConditGroup(ROLE_ID,opt);
    }

    public void andRoleId(Opt opt,Long roleId) {
        addConditGroup(ROLE_ID,opt,roleId);
    }

    public void andRoleId(Opt opt,List<Long> list) {
        addConditGroup(ROLE_ID,opt,list);
    }

    public void andRoleId(Opt opt,Long firstParam,Long secondParam) {
        addConditGroup(ROLE_ID,opt,firstParam,secondParam);
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