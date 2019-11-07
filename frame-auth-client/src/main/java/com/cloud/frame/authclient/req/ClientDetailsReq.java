package com.cloud.frame.authclient.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Map;

/**
 * @author Liulj
 * @version v 0.1 2019/11/7 10:11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("客户端请求信息")
public class ClientDetailsReq {

    @ApiModelProperty("客户端id")
    private String clientId;

    @ApiModelProperty("客户端密码")
    private String clientSecret;

    @ApiModelProperty("授权范围")
    private Set<String> scope = Collections.emptySet();

    @ApiModelProperty("资源id信息")
    private Set<String> resourceIds = Collections.emptySet();

    @ApiModelProperty("授权类型")
    private Set<String> authorizedGrantTypes = Collections.emptySet();

    @ApiModelProperty("重定向uri")
    private Set<String> registeredRedirectUris;

    @ApiModelProperty("autoApproveScopes")
    private Set<String> autoApproveScopes;

    @ApiModelProperty("accessToken有效时间")
    private Integer accessTokenValiditySeconds;

    @ApiModelProperty("refreshToken有效时间")
    private Integer refreshTokenValiditySeconds;

    @ApiModelProperty("额外信息配置")
    private Map<String, Object> additionalInformation = new LinkedHashMap<String, Object>();

}
