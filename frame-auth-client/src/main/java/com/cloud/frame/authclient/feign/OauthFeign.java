package com.cloud.frame.authclient.feign;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "自定义oauth2接口")
@FeignClient(value = "${frame.auth.server:frame-auth-server}")
public interface OauthFeign {

    @GetMapping("/oauth/logout")
    @ApiOperation(value = "注销登录" , notes = "author: llj")
    Boolean loginOut(@RequestParam("token") String token);

}
