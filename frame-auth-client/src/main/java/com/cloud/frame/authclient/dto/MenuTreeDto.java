package com.cloud.frame.authclient.dto;

import com.cloud.frame.authclient.util.TreeBuilder;
import com.cloud.ftl.ftlbasic.annotation.PrimaryKey;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel("MenuTreeDto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuTreeDto extends TreeBuilder.Node {

    @ApiModelProperty("主键")
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

}
