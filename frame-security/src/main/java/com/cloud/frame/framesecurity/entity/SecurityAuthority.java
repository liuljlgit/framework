package com.cloud.frame.framesecurity.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Liulj
 * @version v 0.1 2019/11/6 10:47
 */
@Data
@ApiModel("自定义的GrantedAuthority")
public class SecurityAuthority implements GrantedAuthority {

    private static final long serialVersionUID = -8654263543826465933L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("角色名称")
    private String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
