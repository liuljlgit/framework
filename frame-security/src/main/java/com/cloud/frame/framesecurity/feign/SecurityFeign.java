package com.cloud.frame.framesecurity.feign;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Validated
@Api(tags = "1、用户表")
@FeignClient(name = "${frame.auth.server:frame-auth-server}")
public interface SecurityFeign {

    @GetMapping(value = "/security/resourcegatewayprefix")
    @ApiOperation(value = "获取资源ID对应的前缀" , notes = "author: llj")
    Map<Object, Object> getResourceGatewayPrefixMap();

    @GetMapping(value = "/security/rolekeydetails")
    @ApiOperation(value = "获取角色对应的URL权限" , notes = "author: llj")
    Map<Object, Object> getRolekeyDetailsMap();

    @GetMapping(value = "/security/loginuser")
    @ApiOperation(value = "根据账号查询登录用户信息" , notes = "author: llj")
    @ApiImplicitParam(name="principal", value="账号",required = true)
    Object getLoginUserByPrincipal(@RequestParam("principal") @NotNull String principal);
}
