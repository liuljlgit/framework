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
@ApiModel("ComRole")
public class ComRole extends BaseQuery {

	@ApiModelProperty("")
    @PrimaryKey
    private Long roleId;

	@ApiModelProperty("角色名")
    private String roleName;

	@ApiModelProperty("对应的菜单id，前后逗号分隔，如”,2,22,11,221,“'")
    private String menuIds;

	@ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

	@ApiModelProperty("状态：正常/已删除")
    private Byte isEnable;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String ROLE_ID = "role_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String ROLE_NAME = "role_name";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String MENU_IDS = "menu_ids";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String CREATE_TIME = "create_time";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String IS_ENABLE = "is_enable";


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

    public void andRoleName(Opt opt) {
        addConditGroup(ROLE_NAME,opt);
    }

    public void andRoleName(Opt opt,String roleName) {
        addConditGroup(ROLE_NAME,opt,roleName);
    }

    public void andRoleName(Opt opt,List<String> list) {
        addConditGroup(ROLE_NAME,opt,list);
    }

    public void andRoleName(Opt opt,String firstParam,String secondParam) {
        addConditGroup(ROLE_NAME,opt,firstParam,secondParam);
    }

    public void andMenuIds(Opt opt) {
        addConditGroup(MENU_IDS,opt);
    }

    public void andMenuIds(Opt opt,String menuIds) {
        addConditGroup(MENU_IDS,opt,menuIds);
    }

    public void andMenuIds(Opt opt,List<String> list) {
        addConditGroup(MENU_IDS,opt,list);
    }

    public void andMenuIds(Opt opt,String firstParam,String secondParam) {
        addConditGroup(MENU_IDS,opt,firstParam,secondParam);
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