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
@ApiModel("ComForm")
public class ComForm extends BaseQuery {

	@ApiModelProperty("主键")
    @PrimaryKey
    private Long formId;

	@ApiModelProperty("菜单ID")
    private Long menuId;

	@ApiModelProperty("表单名称")
    private String formName;

	@ApiModelProperty("描述")
    private String descp;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String FORM_ID = "form_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String MENU_ID = "menu_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String FORM_NAME = "form_name";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String DESCP = "descp";


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

    public void andFormName(Opt opt) {
        addConditGroup(FORM_NAME,opt);
    }

    public void andFormName(Opt opt,String formName) {
        addConditGroup(FORM_NAME,opt,formName);
    }

    public void andFormName(Opt opt,List<String> list) {
        addConditGroup(FORM_NAME,opt,list);
    }

    public void andFormName(Opt opt,String firstParam,String secondParam) {
        addConditGroup(FORM_NAME,opt,firstParam,secondParam);
    }

    public void andDescp(Opt opt) {
        addConditGroup(DESCP,opt);
    }

    public void andDescp(Opt opt,String descp) {
        addConditGroup(DESCP,opt,descp);
    }

    public void andDescp(Opt opt,List<String> list) {
        addConditGroup(DESCP,opt,list);
    }

    public void andDescp(Opt opt,String firstParam,String secondParam) {
        addConditGroup(DESCP,opt,firstParam,secondParam);
    }

}