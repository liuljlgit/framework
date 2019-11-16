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
@ApiModel("ComMenu")
public class ComMenu extends BaseQuery {

	@ApiModelProperty("主键")
    @PrimaryKey
    private Long menuId;

	@ApiModelProperty("菜单名")
    private String menuName;

	@ApiModelProperty("父级菜单id")
    private Long parMenuId;

	@ApiModelProperty("链接")
    private String url;

	@ApiModelProperty("图标")
    private String icon;

	@ApiModelProperty("排序权重")
    private Byte weight;

	@ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

	@ApiModelProperty("状态：正常/已删除")
    private Byte isEnable;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String MENU_ID = "menu_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String MENU_NAME = "menu_name";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String PAR_MENU_ID = "par_menu_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String URL = "url";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String ICON = "icon";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String WEIGHT = "weight";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String CREATE_TIME = "create_time";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String IS_ENABLE = "is_enable";


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

    public void andMenuName(Opt opt) {
        addConditGroup(MENU_NAME,opt);
    }

    public void andMenuName(Opt opt,String menuName) {
        addConditGroup(MENU_NAME,opt,menuName);
    }

    public void andMenuName(Opt opt,List<String> list) {
        addConditGroup(MENU_NAME,opt,list);
    }

    public void andMenuName(Opt opt,String firstParam,String secondParam) {
        addConditGroup(MENU_NAME,opt,firstParam,secondParam);
    }

    public void andParMenuId(Opt opt) {
        addConditGroup(PAR_MENU_ID,opt);
    }

    public void andParMenuId(Opt opt,Long parMenuId) {
        addConditGroup(PAR_MENU_ID,opt,parMenuId);
    }

    public void andParMenuId(Opt opt,List<Long> list) {
        addConditGroup(PAR_MENU_ID,opt,list);
    }

    public void andParMenuId(Opt opt,Long firstParam,Long secondParam) {
        addConditGroup(PAR_MENU_ID,opt,firstParam,secondParam);
    }

    public void andUrl(Opt opt) {
        addConditGroup(URL,opt);
    }

    public void andUrl(Opt opt,String url) {
        addConditGroup(URL,opt,url);
    }

    public void andUrl(Opt opt,List<String> list) {
        addConditGroup(URL,opt,list);
    }

    public void andUrl(Opt opt,String firstParam,String secondParam) {
        addConditGroup(URL,opt,firstParam,secondParam);
    }

    public void andIcon(Opt opt) {
        addConditGroup(ICON,opt);
    }

    public void andIcon(Opt opt,String icon) {
        addConditGroup(ICON,opt,icon);
    }

    public void andIcon(Opt opt,List<String> list) {
        addConditGroup(ICON,opt,list);
    }

    public void andIcon(Opt opt,String firstParam,String secondParam) {
        addConditGroup(ICON,opt,firstParam,secondParam);
    }

    public void andWeight(Opt opt) {
        addConditGroup(WEIGHT,opt);
    }

    public void andWeight(Opt opt,Byte weight) {
        addConditGroup(WEIGHT,opt,weight);
    }

    public void andWeight(Opt opt,List<Byte> list) {
        addConditGroup(WEIGHT,opt,list);
    }

    public void andWeight(Opt opt,Byte firstParam,Byte secondParam) {
        addConditGroup(WEIGHT,opt,firstParam,secondParam);
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