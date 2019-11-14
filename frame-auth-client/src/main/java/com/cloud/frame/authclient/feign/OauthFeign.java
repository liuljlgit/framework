package com.cloud.frame.authclient.feign;

import com.cloud.frame.authclient.req.RegisterReq;
import com.cloud.frame.authclient.req.UserReq;
import com.cloud.frame.authclient.resp.LoginResp;
import com.cloud.frame.authclient.resp.UserInfoResp;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Api(tags = "自定义oauth2接口")
@FeignClient(value = "${frame.auth.server:frame-auth-server}")
public interface OauthFeign {

    @PostMapping("/oauth/login")
    @ApiOperation(value = "登录" , notes = "author: llj")
    LoginResp login(@RequestBody UserReq userReq);

    @PostMapping("/oauth/register")
    @ApiOperation(value = "注册" , notes = "author: llj")
    CommonResp<Object> register(@RequestBody @Valid RegisterReq registerReq);

    @GetMapping("/oauth/logout")
    @ApiOperation(value = "注销登录" , notes = "author: llj")
    Boolean loginOut();

    @GetMapping("/oauth/refreshtoken")
    @ApiOperation(value = "刷新token" , notes = "author: llj")
    LoginResp refreshtoken(@ApiParam("需要拿refresh_token") @RequestParam("token") String token);

    @GetMapping("/oauth/userinfo")
    @ApiOperation(value = "根据用户的token返回用户信息，菜单，权限等信息")
    UserInfoResp getLoginUserInfo();

}
