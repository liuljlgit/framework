package com.cloud.frame.authclient.entity;

import com.cloud.ftl.ftlbasic.annotation.PrimaryKey;
import com.cloud.ftl.ftlbasic.constant.PatternConst;
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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("ComRole")
public class ComRole extends BaseQuery {

	@ApiModelProperty("主键")
    @PrimaryKey
    private Long roleId;

	@ApiModelProperty("角色编码")
    private String roleCode;

	@ApiModelProperty("角色名")
    private String roleName;

	@ApiModelProperty("允许通过的url")
    private String permitUrls;

	@ApiModelProperty("拒绝通过的url")
    private String forbidUrls;

	@ApiModelProperty("创建时间")
    @JsonFormat(pattern = PatternConst.NORM_DATETIME,timezone = PatternConst.TIMEZONE)
    private Date createTime;

	@ApiModelProperty("状态：正常/已删除")
    private Byte isEnable;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String ROLE_ID = "role_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String ROLE_CODE = "role_code";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String ROLE_NAME = "role_name";

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


    public void andRoleId(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(ROLE_ID,opt);
        } else if(values.length == 1){
            addConditGroup(ROLE_ID,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(ROLE_ID,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ ROLE_ID + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andRoleCode(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(ROLE_CODE,opt);
        } else if(values.length == 1){
            addConditGroup(ROLE_CODE,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(ROLE_CODE,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ ROLE_CODE + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andRoleName(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(ROLE_NAME,opt);
        } else if(values.length == 1){
            addConditGroup(ROLE_NAME,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(ROLE_NAME,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ ROLE_NAME + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andPermitUrls(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(PERMIT_URLS,opt);
        } else if(values.length == 1){
            addConditGroup(PERMIT_URLS,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(PERMIT_URLS,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ PERMIT_URLS + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andForbidUrls(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(FORBID_URLS,opt);
        } else if(values.length == 1){
            addConditGroup(FORBID_URLS,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(FORBID_URLS,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ FORBID_URLS + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andCreateTime(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(CREATE_TIME,opt);
        } else if(values.length == 1){
            addConditGroup(CREATE_TIME,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(CREATE_TIME,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ CREATE_TIME + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andIsEnable(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(IS_ENABLE,opt);
        } else if(values.length == 1){
            addConditGroup(IS_ENABLE,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(IS_ENABLE,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ IS_ENABLE + "’ 的SQL入参个数不正确 ");
        }
    }

}