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
@ApiModel("ComMenuRole")
public class ComMenuRole extends BaseQuery {

	@ApiModelProperty("主键")
    @PrimaryKey
    private Long mrId;

	@ApiModelProperty("角色主键")
    private Long roleId;

	@ApiModelProperty("菜单主键")
    private Long menuId;

	@ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String MR_ID = "mr_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String ROLE_ID = "role_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String MENU_ID = "menu_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String CREATE_TIME = "create_time";


    public void andMrId(Opt opt) {
        addConditGroup(MR_ID,opt);
    }

    public void andMrId(Opt opt,Long mrId) {
        addConditGroup(MR_ID,opt,mrId);
    }

    public void andMrId(Opt opt,List<Long> list) {
        addConditGroup(MR_ID,opt,list);
    }

    public void andMrId(Opt opt,Long firstParam,Long secondParam) {
        addConditGroup(MR_ID,opt,firstParam,secondParam);
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

    public void andMenuId(Opt opt) {
        addConditGroup(MENU_ID,opt);
    }

    public void andMenuId(Opt opt,Long menuId) {
        addConditGroup(MENU_ID,opt,menuId);
    }

    public void andMenuId(Opt opt,List<Long> list) {
        addConditGroup(MENU_ID,opt,list);
    }

    public void andMenuId(Opt opt,Long firstParam,Long secondParam) {
        addConditGroup(MENU_ID,opt,firstParam,secondParam);
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