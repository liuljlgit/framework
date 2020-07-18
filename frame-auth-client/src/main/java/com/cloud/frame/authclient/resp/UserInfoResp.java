package com.cloud.frame.authclient.resp;

import com.cloud.frame.authclient.entity.ComMenu;
import com.cloud.frame.authclient.entity.ComRole;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Liulj
 * @version v 0.1 2019/11/6 15:26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("与用户关联的信息")
public class UserInfoResp implements Serializable {

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("角色列表")
    private List<ComRole> roleList = Lists.newArrayList();

    @ApiModelProperty("菜单列表")
    private List<ComMenu> menuList = Lists.newArrayList();
}
