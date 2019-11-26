package com.cloud.frame.authclient.entity;

import com.cloud.ftl.ftlbasic.annotation.PrimaryKey;
import com.cloud.ftl.ftlbasic.enums.Opt;
import com.cloud.ftl.ftlbasic.webEntity.BaseQuery;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("ComFormRole")
public class ComFormRole extends BaseQuery {

	@ApiModelProperty("主键")
    @PrimaryKey
    private Long rfId;

	@ApiModelProperty("角色ID")
    private Long roleId;

	@ApiModelProperty("表单ID")
    private Long formId;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String RF_ID = "rf_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String ROLE_ID = "role_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String FORM_ID = "form_id";


    public void andRfId(Opt opt) {
        addConditGroup(RF_ID,opt);
    }

    public void andRfId(Opt opt,Long rfId) {
        addConditGroup(RF_ID,opt,rfId);
    }

    public void andRfId(Opt opt,List<Long> list) {
        addConditGroup(RF_ID,opt,list);
    }

    public void andRfId(Opt opt,Long firstParam,Long secondParam) {
        addConditGroup(RF_ID,opt,firstParam,secondParam);
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

    public void andFormId(Opt opt) {
        addConditGroup(FORM_ID,opt);
    }

    public void andFormId(Opt opt,Long formId) {
        addConditGroup(FORM_ID,opt,formId);
    }

    public void andFormId(Opt opt,List<Long> list) {
        addConditGroup(FORM_ID,opt,list);
    }

    public void andFormId(Opt opt,Long firstParam,Long secondParam) {
        addConditGroup(FORM_ID,opt,firstParam,secondParam);
    }

}