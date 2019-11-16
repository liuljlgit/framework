package com.cloud.frame.authclient.entity;

import com.cloud.ftl.ftlbasic.annotation.PrimaryKey;
import com.cloud.ftl.ftlbasic.enums.Opt;
import com.cloud.ftl.ftlbasic.webEntity.BaseQuery;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("ComUserRole")
public class ComUserRole extends BaseQuery {

	@ApiModelProperty("主键")
    @PrimaryKey
    private Long urId;

	@ApiModelProperty("用户主键")
    private Long userId;

	@ApiModelProperty("权限主键")
    private Long roleId;

	@ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String UR_ID = "ur_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String USER_ID = "user_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String ROLE_ID = "role_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String CREATE_TIME = "create_time";


    public void andUrId(Opt opt) {
        addConditGroup(UR_ID,opt);
    }

    public void andUrId(Opt opt,Long urId) {
        addConditGroup(UR_ID,opt,urId);
    }

    public void andUrId(Opt opt,List<Long> list) {
        addConditGroup(UR_ID,opt,list);
    }

    public void andUrId(Opt opt,Long firstParam,Long secondParam) {
        addConditGroup(UR_ID,opt,firstParam,secondParam);
    }

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